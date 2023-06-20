package Helden

import Gegner.Opponent
import SLEEP_TIME
import bag
import chars
import enemys


// klasse für helden
open class Hero(var name: String, var hp: Int = 100, var level: Int = 5, var dmg: Int) {

    var LEVEL = 5
    var HP = 100
    var STARTDMG = 50

    open var startLevel = (5 until 10).random()
    open var resurce: Int = 0
    open var maxResource: Int = 100
    open val maxHP = level * hp
    open var currentHP = maxHP
    open var attacke = mutableMapOf<String, Int>()
    open var critChanze = 50
    open var classPlayer = ""
    open var specialAttackCost: Int? = null
    open var thisResource = ""
    open var aoeCost: Int? = null
    open var rescueRecovery: Int? = null

    open fun attackSelection(attack: Map<String, Int>) {

    }

     private fun standardAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(0)
        val resourceNew = this.resurce + this.rescueRecovery!!
        var crit = (1..100).random()

        if (resourceNew > this.maxResource) {
            this.resurce = this.maxResource
        } else {
            this.resurce += this.rescueRecovery!!
        }

        if (crit <= critChanze) {
            var damage = attack[attacke]!! * 2
            target.hp -= damage
            println("Sie haben $attacke gewählt.")
            println("'${this.name}' greift '${target.name}' mit '$attacke' an, der ${attack[attacke]} verursacht.")
            println()
            println("--- ${target.name} wird kritischen getroffen und bekommt doppelten schaden $damage ---")
        } else {
            target.hp -= attack[attacke]!!
            println("Sie haben $attacke gewählt.")
            println("'${this.name}' greift '${target.name}' mit '$attacke' an")
            println()
            println("--- ${target.name} erleidet ${attack[attacke]} schaden---")
        }

        if (target.hp <= 0) {
            println("${target.name} wurde besiegt")
            println()
            enemys.remove(target)
        }
        Thread.sleep(SLEEP_TIME)

    }

    open fun spezialAttack(target: Opponent, player: MutableList<Hero>, attack: Map<String, Int>,a: Int, b: Int) {}

    private fun aoeDmgHero(attack: Map<String, Int>, a: Int, b: Int) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(2)
        var deadInFight = mutableListOf<Opponent>()
        println()
        println("'${this.name}' greift mit '${attacke}' an und richtet ${attack[attacke]} schaden an allen gegnern an.")
        println()
        for (enemy in enemys) {
            val crit = (1..100).random()
            if (crit <= critChanze) {
                val damage = attack[attacke]!! * 2
                enemy.hp -= damage
                println("--- ${enemy.name} erleidet doppelten schade ${damage} ---")
                if (enemy.hp <= 0) {
                    println()
                    println("${enemy.name} wurde besiegt")
                    println()
                    deadInFight.add(enemy)
                }
            } else {
                enemy.hp -= attack[attacke]!!
                println("--- ${enemy.name} erleidet ${attack[attacke]} schaden---")
                if (enemy.hp <= 0) {
                    println()
                    println("${enemy.name} wurde besiegt")
                    println()
                    deadInFight.add(enemy)
                }
            }
        }
        enemys.removeAll(deadInFight)
        println()
        Thread.sleep(SLEEP_TIME)
        this.resurce -= this.aoeCost!!
    }

    open fun attack(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        this.resurce = a
        this.maxResource = b
        this.level = startLevel

        println("--- ${this.classPlayer} ist an der Reihe ---")
        this.attackSelection(attack)

        var eingabe: Int? = null
        do {

            try {
                eingabe = readln().toInt()

                when (eingabe) {

                    1 -> {
                        this.standardAttack(target, attack, a, b)
                        break
                    }

                    2 -> {
                        if (a >= this.specialAttackCost!!) {
                            this.spezialAttack(target, chars, attack, a, b)
                            break
                        } else {
                            println("Sie haben nicht genug $thisResource")
                            println("Die standert Attacke wird ausgeführt.")
                            this.standardAttack(target, attack, a, b)
                        }
                    }

                    3 -> {
                        if (a >= this.aoeCost!!) {
                            this.aoeDmgHero(attack, a, b)

                        } else {
                            println("Sie haben nicht genug $thisResource")
                            println("Die standert Attacke wird ausgeführt.")
                            this.standardAttack(target, attack, a, b)
                        }
                    }

                    4 -> {
                        inventory(target, attack, a, b)
//                TODO Beutel einfügen
                    }

                }
            } catch (e: Exception) {
                println("Falsche Eingabe: Bitte geben sie eine Zahl zwischen 1 und 4 ein!!")
            }

        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
    }

    open fun inventory(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        println("In Ihrem Inventar sind folgende Gegenstände:")
        println()
        for (i in bag){
            println("${bag.indexOf(i) + 1} - ${i.name} - Anzahl: ${i.anzahl}")
        }
        println("0 - Zurück")

        println("Welchen gegenstand wollen Sie benutzen?")

        var input: Int? = null

        while (input !in listOf(0,1, 2)) {
            try {
                input = readln().toInt()

            } catch (e: NumberFormatException) {
                println("Ungültige Eingabe. Bitte geben Sie 1 oder 2 ein, oder 0 für zurück.")
            }
        }
        if (input == 0){
            this.attack(target, attack, a, b)
            var needPoition = chars.filter { it.currentHP < it.maxHP }
// TODO funktionen zum heilen einfügen!!!!!!
        }

    }
    
    }







