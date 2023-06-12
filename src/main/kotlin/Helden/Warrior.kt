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
        println()
        println("Sie haben folgende Optionen")
        println("1 - ${atkNamen.elementAt(0)}")
        println("2 - ${atkNamen.elementAt(1)}")
        println("3 - ${atkNamen.elementAt(2)}")
        println("4 - Beutel")

        var eingabe = readln()
        var index = eingabe.toInt() - 1

        var attacke = atkNamen.elementAt(index)
        println("Sie haben $attacke gewählt.")
        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")

        boss.bossHP -= warriorAtk[attacke]!!

        println("--- ${boss.name} erleidet schaden ${warriorAtk[attacke]}---")
        Thread.sleep(SLEEP_TIME / 2)
        println("Name: ${boss.name}\t")
        println("HP: ${boss.bossHP}/${boss.bossMaxHP}")

    }
//    TODO: Beutel anlegen, die anderen helden generieren
}
