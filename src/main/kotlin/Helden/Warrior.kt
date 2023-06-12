package Helden

import SLEEP_TIME
import boss

class Warrior(level: Int, hp: Int) : Hero(hp, level) {

    var startLevel = (5 until 10).random()
    var name: String = ""
    var maxHP: Int = hp * startLevel
    var startHP = maxHP
    private var currHP: Int = hp
    var rage: Int = 0
    var maxRage: Int = 100
    var dmg = 20 * startLevel


    init {
        println("Bitte geben sie den Namen für Ihren Krieger ein")
        this.name = readln()
    }

    val warriorAtk = mutableMapOf<String, Int>(
        "Hieb" to dmg * 2,
        "Blutung" to dmg,
        "Wirbeln" to dmg
    )

    fun warriorAtk() {
        var atkNamen = warriorAtk.keys
        println("--- Krieger ist an der Reihe ---")
        println("Name: ${this.name}\tLevel: ${this.startLevel}")
        println("HP: ${this.startHP}/${this.maxHP}\tWut: ${this.rage}/${this.maxRage}")

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

                    1, 2 , 3 -> {
                        println("Sie haben $attacke gewählt.")
                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
                        boss.bossHP -= warriorAtk[attacke]!!
                    }

//                    2 -> {
//                        println("Sie haben $attacke gewählt.")
//                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
//                        boss.bossHP -= warriorAtk[attacke]!!
//                    }
//
//                    3 -> {
//                        println("Sie haben $attacke gewählt.")
//                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
//                        boss.bossHP -= warriorAtk[attacke]!!
//                    }

                    4 -> {
//                TODO Beutel einfügen
                    }

                    else -> {
                        println("Falsche Eingabe")
                    }

                }

                println("--- ${boss.name} erleidet schaden ${warriorAtk[attacke]}---")
                Thread.sleep(SLEEP_TIME / 2)
                println("Name: ${boss.name}\t")
                println("HP: ${boss.bossHP}/${boss.bossMaxHP}")

            } catch (e: Exception) {
                println("Falsche Eingabe")
            }

        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
    }
}
