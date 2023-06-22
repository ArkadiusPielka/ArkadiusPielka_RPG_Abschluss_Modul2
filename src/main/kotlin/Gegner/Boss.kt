package Gegner

import Helden.Hero
import SLEEP_TIME
import bossHelper
import createBossHelper
import chars
import deadChars
import enemys


class Boss(name: String, hp: Int, maxHP: Int) : Opponent(name, hp, maxHP) {


    override var attack = mutableMapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300,
        "Beschwören" to 0,
        "Heilung" to 0

    )

    fun aoeDamage(attack: Map<String, Int>) {
        val atkNamen = "Feueratem"
        var deadInFight = mutableListOf<Hero>()
        println("'${this.name}' greift alle mit '$atkNamen' an und richtet ${attack[atkNamen]} schaden an")
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
//        var attacke = atkNamen.elementAt(2)
//        if (attacke == "Beschwören") {
        println("'${this.name}' führt eine '$atkNamen' durch.")
        println()
        createBossHelper()
        enemys.add(bossHelper)
        attack.remove(atkNamen)
//        }
    }

    fun bossHeal() {
        var atkNamen = "Heilung"
        println("'${this.name}' führt eine '$atkNamen' durch, und heilt sich um 50%")
        this.hp = this.hp + this.maxHP / 2
    }

    override fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {

        var atkNamen = attack.keys.toList().random()
        var hero = chars.random()
        var crit = (1..100).random()

        while (atkNamen == "Heilung" && (hp <= maxHP / 2 && crit < 50)) {
            atkNamen = attack.keys.toList().random()
        }

        println("--- ${this.name} ist an der Reihe ---")
        println()
        Thread.sleep(SLEEP_TIME)
        if (atkNamen == "Feueratem") {
            this.aoeDamage(attack)

            println()
        }
        if (atkNamen == "Heilung"){
            bossHeal()

        }
        if (atkNamen == "Beschwören") {
            this.spezialAttackBoss()

        } else if (atkNamen == "Kralle"){
            while (hero.isDead) {
                hero = chars.random()
            }
            println("'${this.name}' greift '${hero.name}' mit '$atkNamen' und richtet ${attack[atkNamen]} schaden an")
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


