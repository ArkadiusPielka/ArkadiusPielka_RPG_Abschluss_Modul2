package Gegner

import Helden.Hero
import SLEEP_TIME
import bossHelper
import createBossHelper
import chars
import deadChars
import enemys


class Boss(name: String, hp: Int, maxHP: Int) : Opponent(name, hp, maxHP) {

    var dotActiv: Boolean = false

    override var attack = mutableMapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300,
        "Beschwören" to 0,
        "Blutung" to 100
    )

    fun aoeDamage(target: MutableList<Hero>, attack: Map<String, Int>) {
        val atkNamen = "Feueratem"
        var deadInFight = mutableListOf<Hero>()
        println("'${this.name}' greift alle mit '$atkNamen' an und richtet ${attack[atkNamen]} schaden an")
        for (char in target) {
            if (char.isDead) {
                continue
            }
            char.currentHP -= attack[atkNamen]!!
            if (char.currentHP <= 0) {
                deadInFight.add(char)
                println("${char.name} wurde besiegt")
                char.currentHP = 0
                char.isDead = true
            } else {
                println("${char.name} hat noch ${char.currentHP}/${char.maxHP}")
            }
        }
        deadChars.addAll(deadInFight)
//        chars.removeAll(deadInFight)
    }


    fun spezialAttackBoss() {
        var atkNamen = "Beschwören"
//        var attacke = atkNamen.elementAt(2)
//        if (attacke == "Beschwören") {
        println("'${this.name}' führt eine Beschwörung durch.")
        println()
        createBossHelper()
        enemys.add(bossHelper)
        attack.remove("Beschwören")
//        }
    }

    fun dot(target: MutableList<Hero>, attack: Map<String, Int>) {
        val atkNamen = "Blutung"
        var deadInFight = mutableListOf<Hero>()
        println()
        for (char in target) {
            if (char.isDead) {
                continue
            }
            println("'${this.name}' greift '${char.name}' mit '$atkNamen' und richtet ${attack[atkNamen]} schaden an")
            println("'${char.name}' erleidet eine offene Wunde die pro Runde  ${attack[atkNamen]?.div(2)} schaden anrichtet")
            char.currentHP -= attack[atkNamen]!!
            if (char.currentHP <= 0) {
                deadInFight.add(char)
                println("${char.name} wurde besiegt")
                char.currentHP = 0
                char.isDead = true
            } else {
                println("${char.name} hat noch ${char.currentHP}/${char.maxHP}")
            }
        }
        dotActiv = true
        deadChars.addAll(deadInFight)
//        chars.removeAll(deadInFight)
    }

    override fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {
        var atkNamen = attack.keys.toList().random()
        var hero = chars.random()

        println("--- ${this.name} ist an der Reihe ---")
        println()
        Thread.sleep(SLEEP_TIME)
        if (atkNamen == "Feueratem") {
            this.aoeDamage(target, attack)

            println()
        } else if (atkNamen == "Beschwören") {
            this.spezialAttackBoss()

        } else {
            while (true) {
                if (!hero.isDead) {
                    println("'${this.name}' greift '${hero.name}' mit '$atkNamen' und richtet ${attack[atkNamen]} schaden an")
                    hero.currentHP -= attack[atkNamen]!!
                    println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
                    println()
                }
                if (hero.currentHP <= 0) {
                    deadChars.add(hero)
//                chars.remove(hero)
                    println("${hero.name} wurde besiegt")
                    hero.currentHP = 0
                    hero.isDead = true
                    println()
                }
                break
            }
        }
        Thread.sleep(SLEEP_TIME)
    }
}


