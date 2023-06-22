package Gegner

import Helden.Hero
import SLEEP_TIME
import bossHelper
import createBossHelper
import chars
import deadChars
import enemys


class Boss(name: String, hp: Int, maxHP: Int) : Opponent(name, hp, maxHP) {

    var dotActiv = false

    override var attack = mutableMapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300,
        "Beschwören" to 0,
        "Heilung" to 0,
        "Blutung" to 100

    )

    fun dot(attack: Map<String, Int>) {
        val atkNamen = "Blutung"
        var deadInFight = mutableListOf<Hero>()
        var hero = chars.random()

        while (hero.isDead) {
            hero = chars.random()
        }

        println("'${this.name}' greift '${hero.name}' mit '$atkNamen' an und verursacht ${attack[atkNamen]} Schaden")
        println("'${hero.name}' erleidet eine offene Wunde, die pro Runde ${attack[atkNamen]?.div(2)} Schaden anrichtet")
        println()
        hero.currentHP -= attack[atkNamen]!!

        if (hero.currentHP <= 0) {
            deadInFight.add(hero)
            println("${hero.name} wurde besiegt")
            hero.currentHP = 0
            hero.isDead = true
        } else {
            println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
        }
        hero.hasDebuff = true
        println()
        dotActiv = true
        deadChars.addAll(deadInFight)

    }

    fun aoeDamage(attack: Map<String, Int>) {
        val atkNamen = "Feueratem"
        var deadInFight = mutableListOf<Hero>()
        println("'${this.name}' greift alle mit '$atkNamen' an und verursacht ${attack[atkNamen]} Schaden")
        for (char in chars) {
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
    }


    fun spezialAttackBoss() {
        var atkNamen = "Beschwören"

        println("'${this.name}' führt eine '$atkNamen' durch.")
        println()
        createBossHelper()
        enemys.add(bossHelper)
        attack.remove(atkNamen)
    }

    fun bossHeal() {
        var atkNamen = "Heilung"
        println("'${this.name}' führt eine '$atkNamen' durch, und heilt sich um 50%")
        val heal = this.hp + this.maxHP / 2
        if (heal > this.maxHP){
            this.hp = this.maxHP
        }
        this.hp = this.hp + this.maxHP / 2
        println()
    }

    override fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {

        var atkNamen = attack.keys.toList().random()
        var hero = chars.random()
        var crit = (1..100).random()

        while (atkNamen == "Heilung" && (hp <= maxHP / 2 && crit < 30)) {
            atkNamen = attack.keys.toList().random()
        }
        println()
        println("--- ${this.name} ist an der Reihe ---")
        println()
        Thread.sleep(SLEEP_TIME)
        if (atkNamen == "Blutung") {
            this.dot(attack)

            println()
        }
        if (atkNamen == "Feueratem") {
            this.aoeDamage(attack)

            println()
        }
        if (atkNamen == "Heilung") {
            bossHeal()

        }
        if (atkNamen == "Beschwören") {
            this.spezialAttackBoss()

        } else if (atkNamen == "Kralle") {
            while (hero.isDead) {
                hero = chars.random()
            }
            println("'${this.name}' greift '${hero.name}' mit '$atkNamen' an und verursacht ${attack[atkNamen]} Schaden")
            hero.currentHP -= attack[atkNamen]!!
            println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
            println()
            if (hero.currentHP <= 0) {
                deadChars.add(hero)
                println("${hero.name} wurde besiegt")
                hero.currentHP = 0
                hero.isDead = true
                println()
            }
        }
        Thread.sleep(SLEEP_TIME)
    }
}


