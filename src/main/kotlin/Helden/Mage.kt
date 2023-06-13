package Helden

import SLEEP_TIME
import boss
import monk
import warrior

class Mage(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {


    var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    var startHP = maxHP
    var mana: Int = 0
    var maxMana: Int = 100
    var dmgNwe = 20 * startLevel

    val mageAtk = mutableMapOf<String, Int>(
        "Feuerball" to dmgNwe * 2,
        "heilung" to maxHP / 2,
        "Meteor" to dmgNwe
    )

    init {
        println("Bitte geben sie den Namen für Ihren Magier ein")
        this.name = readln()
    }


    fun mageAtk() {
        var atkNamen = mageAtk.keys
        println("--- Magier ist an der Reihe ---")
        println("Name: ${this.name}\tLevel: ${this.startLevel}")
        println("HP: ${this.currentHP}/${this.maxHP}\tWut: ${this.mana}/${this.maxMana}")

        var eingabe: Int = 0
        do {
            println()
            println("Sie haben folgende Optionen")
            println("1 - ${atkNamen.elementAt(0)}")
            println("2 - ${atkNamen.elementAt(1)}")
            println("3 - ${atkNamen.elementAt(2)}")
            println("4 - Beutel")

            try {
                eingabe = readln().toInt()
                var index = eingabe - 1

                var attacke = atkNamen.elementAt(index)
                when (eingabe) {

                    1 -> {
                        println("Sie haben $attacke gewählt.")
                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
                        boss.bossHP -= mageAtk[attacke]!!
                        println()
                    }

                    2 -> {
                            println("'${this.name}' Heilt alle mit '$attacke' an")
                        if (warrior.currentHP > hp) {
                            println("${warrior.name} wurde geheilt ${warrior.maxHP}/${warrior.maxHP}")
                        }
                        if (this.currentHP > hp){
                            println("${this.name} wurde geheilt ${this.maxHP}/${this.maxHP}")
                        }
                        if (monk.currentHP > hp) {
                            println("${monk.name} wurde geheilt ${monk.maxHP}/${monk.maxHP}")
                        }
                        else {
                            warrior.currentHP += mageAtk[attacke]!!
                            monk.currentHP += mageAtk[attacke]!!
                            this.currentHP += mageAtk[attacke]!!
                            println("${warrior.name} wurde geheilt ${warrior.currentHP}/${warrior.maxHP}")
                            println("${monk.name} wurde geheilt ${monk.currentHP}/${monk.maxHP}")
                            println("${this.name} wurde geheilt ${this.currentHP}/${this.maxHP}")
                            println()
                        }
                    }

                    3 -> {
                        println("Sie haben $attacke gewählt.")
                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
                        boss.bossHP -= mageAtk[attacke]!!
                        println()
                    }

                    4 -> {
//                TODO Beutel einfügen
                    }

                    else -> {
                        println("Falsche Eingabe")
                    }

                }

                println("--- ${boss.name} erleidet ${mageAtk[attacke]} schaden---")
                Thread.sleep(SLEEP_TIME / 2)
                println("Name: ${boss.name}\t")
                println("HP: ${boss.bossHP}/${boss.bossMaxHP}")

            } catch (e: Exception) {
                println("Falsche Eingabe")
            }

        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
    }
}



