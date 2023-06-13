package Helden

import Gegner.Boss
import Gegner.Opponent
import SLEEP_TIME


// klasse für helden
open class Hero(var name: String, var hp: Int = 100, var level: Int = 5, var dmg: Int) {

    open var startLevel = (5 until 10).random()
    open var resurse: Int = 0
    open var maxResurse: Int = 100
    open val maxHP = level * hp
    open var currentHP = maxHP
    open var numberOfHits = 0

    open fun attack(target: Boss, attack: Map<String, Int>, a: Int, b: Int, numberOfHits: Int) {
//        val attack: MutableMap<String, Int> = mutableMapOf()
        this.resurse = a
        this.maxResurse = b
        this.hp = hp
        this.level = startLevel
        this.numberOfHits =numberOfHits
        var atkNamen = attack.keys
        var atkDmg = attack.values
        println("--- Krieger ist an der Reihe ---")
        println("Name: ${this.name}\tLevel: ${this.level}")
        println("HP: ${this.currentHP}/${this.maxHP}\tWut: ${this.resurse}/${this.maxResurse}")

        var eingabe: Int = 0
        do {
            println()
            println("Sie haben folgende Optionen")
            println("1 - ${atkNamen.elementAt(0)} macht ${atkDmg.elementAt(0)} schaden und stellt 20 deiner resurse her.")
            println("2 - ${atkNamen.elementAt(1)} macht ${atkDmg.elementAt(1)} schaden und kostet 40 von deiner resurse.")
            println("3 - ${atkNamen.elementAt(2)} macht ${atkDmg.elementAt(2)} schaden und kostet 20 von deiner resurse.")
            println("4 - Beutel")

            try {
                eingabe = readln().toInt()
                var index = eingabe - 1

                var attacke = atkNamen.elementAt(index)
                when (eingabe) {

                    1 -> {
                        println("Sie haben $attacke gewählt.")
                        println("'${this.name}' greift '${target.name}' mit '$attacke' an")
                        target.hp -= attack[attacke]!!
                        this.resurse += 20
                        println()
                    }

                    2 -> {
                        println("Sie haben $attacke gewählt, das ist eine Spezial Attacke die $numberOfHits zuschlägt.")
                        if (a >= 40) {
                            for (i in 1..numberOfHits) {
                                val damage = attack[attacke]!!
                                target.hp -= damage
                            }
                            this.resurse -= 40
                            println("'${this.name}' greift '${target.name}' mit '$attacke' an")
                            println()
                        }
                        else {
                            var attackeFehlschlag = atkNamen.elementAt(0)
                            println("Sie haben nicht genug von ihrer resurse")
                            println("Es wurde ${attackeFehlschlag} gewählt.")
                            println("'${this.name}' greift '${target.name}' mit '${attackeFehlschlag}' an")
                            target.hp -= attack[attackeFehlschlag]!!
                            this.resurse += 20
                            println()
                            println("--- ${target.name} erleidet ${attack[attackeFehlschlag]} schaden---")
                            Thread.sleep(SLEEP_TIME / 2)
                            println("Name: ${target.name}\t")
                            println("HP: ${target.hp}/${target.maxHP}")
                            break
                        }
                    }

                    3 -> {
                        println("Sie haben $attacke gewählt.")
                        println("'${this.name}' greift '${target.name}' mit '$attacke' an")
                        target.hp -= attack[attacke]!!
                        println()
                    }

                    4 -> {
//                TODO Beutel einfügen
                    }

                    else -> {
                        println("Falsche Eingabe")
                    }

                }

                println("--- ${target.name} erleidet ${attack[attacke]} schaden---")
                Thread.sleep(SLEEP_TIME / 2)
                println("Name: ${target.name}\t")
                println("HP: ${target.hp}/${target.maxHP}")

            } catch (e: Exception) {
                println("Falsche Eingabe")
            }

        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
    }
}





