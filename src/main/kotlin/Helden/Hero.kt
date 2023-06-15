package Helden

import Gegner.Opponent
import SLEEP_TIME
import chars
import enemys


// klasse für helden
open class Hero(var name: String, var hp: Int = 100, var level: Int = 5, var dmg: Int) {

    var LEVEL = 5
    var HP = 100
    var STARTDMG = 50

    open var startLevel = (5 until 10).random()
    open var resurse: Int = 0
    open var maxResurse: Int = 100
    open val maxHP = level * hp
    open var currentHP = maxHP
    open var thisResurse = ""
    open var classPlayer = ""
    open var resuceRecovery = 0
    open var specialAttackCost = 0
    open var attacke = mutableMapOf<String, Int>()

    open fun standartAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int){}
    open fun spezialAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int) {}

    open fun aoeDmgHero(target: MutableList<Opponent>, attack: Map<String, Int>, a: Int, b: Int){

    }
    open fun spezialAttacOnPlayer(player: MutableList<Hero>, attack: Map<String, Int>, a: Int, b: Int) {

    }

    open fun attack(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        this.resurse = a
        this.maxResurse = b
        this.hp = hp
        this.level = startLevel
        this.thisResurse = thisResurse
        this.classPlayer = classPlayer
        this.specialAttackCost = specialAttackCost

        var atkNamen = attack.keys
        var atkDmg = attack.values
        println("--- ${this.classPlayer} ist an der Reihe ---")
        println("Name: ${this.name}\tLevel: ${this.level}")
        println("HP: ${this.currentHP}/${this.maxHP}\t$thisResurse: ${this.resurse}/${this.maxResurse}")
        println()

        var eingabe: Int = 0
        do {
            println("Sie haben folgende Optionen")
            println("1 - ${atkNamen.elementAt(0)} macht ${atkDmg.elementAt(0)} schaden und stellt ${this.resuceRecovery} ${this.thisResurse} her.")
            println("2 - ${atkNamen.elementAt(1)} macht ${atkDmg.elementAt(1)} schaden und kostet ${this.specialAttackCost} ${this.thisResurse}.")
            println("3 - ${atkNamen.elementAt(2)} macht ${atkDmg.elementAt(2)} schaden und kostet ${this.resuceRecovery} ${this.thisResurse}.")
            println("4 - Beutel")

            try {
                eingabe = readln().toInt()
                var index = eingabe - 1

                var attacke = atkNamen.elementAt(index)
                when (eingabe) {

                    1 -> {
                        if (this.classPlayer == "Krieger") {
                            this.standartAttack(target, attack, a, b)
                        }
                        if (this.classPlayer == "Magier") {
                            this.standartAttack(target, attack, a, b)
                        }
                        if (this.classPlayer == "Mönch") {
                            this.standartAttack(target, attack, a, b)
                        }
//                        println("Sie haben $attacke gewählt.")
//                        println("'${this.name}' greift '${target.name}' mit '$attacke' an")
//                        target.hp -= attack[attacke]!!
//                        val resurceNew = this.resurse + this.resuceRecovery
//                        if (resurceNew > this.maxResurse) {
//                            this.resurse = this.maxResurse
//                        } else {
//                            this.resurse += this.resuceRecovery
//                            println()
//                        }
//                        println("--- ${target.name} erleidet ${attack[attacke]} schaden---")
//                        println("Name: ${target.name}\t")
//                        println("HP: ${target.hp}/${target.maxHP}")
//                        Thread.sleep(SLEEP_TIME)
//                        println()
                    }

                    2 -> {
                        if (this.classPlayer == "Krieger") {
                            this.spezialAttack(target, attack, a, b)
                            break
                        }
                        if (this.classPlayer == "Magier") {
                            this.spezialAttacOnPlayer(chars, attack, a, b)
                            break
                        }
                        if (this.classPlayer == "Mönch") {
                            this.spezialAttack(target, attack, a, b)
                            break
                        }
                    }

                    3 -> {
                        if (attacke == "Wirbelwind") {
                            this.aoeDmgHero(enemys, attack, a, b)
//                            break
                        }
//                        println("Sie haben $attacke gewählt.")
//                        println("'${this.name}' greift '${target.name}' mit '$attacke' an")
//                        target.hp -= attack[attacke]!!
//                        println()
                    }

                    4 -> {
//                TODO Beutel einfügen
                    }

                    else -> {
                        println("Falsche Eingabe")
                    }

                }


            } catch (e: Exception) {
                println("Falsche Eingabe")
            }
            if (target.hp <= 0) {
                enemys.remove(target)
            }
        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
    }



}






