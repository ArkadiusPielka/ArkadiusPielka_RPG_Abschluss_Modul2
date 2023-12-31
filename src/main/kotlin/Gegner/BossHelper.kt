package Gegner

import Helden.Hero
import SLEEP_TIME
import chars
import deadChars


class BossHelper(name: String, hp: Int, maxHP: Int) : Opponent(name, hp, maxHP) {


    override var attack = mutableMapOf<String, Int>(
        "Schwert-Angriff" to 200,
        "Stacheln" to 100
    )

    fun aoeDamage(attack: Map<String, Int>) {
        var atkNamen = "Stacheln"
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
        println()
//        Thread.sleep(SLEEP_TIME)
    }

    override fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {

        var atkNamen = attack.keys.toList().random()
        var hero = chars.random()

        println("--- ${this.name} ist an der Reihe ---")
        println()
        Thread.sleep(SLEEP_TIME)

        if (atkNamen == "Stacheln") {
            this.aoeDamage(attack)
        } else {
            while (hero.isDead) {
                hero = chars.random()
            }
            println("'${this.name}' greift '${hero.name}' mit '$atkNamen' an und verursacht ${attack[atkNamen]} Schaden")
            hero.currentHP -= attack[atkNamen]!!

            if (hero.currentHP <= 0) {
                deadChars.add(hero)
                println("${hero.name} wurde besiegt")
                hero.currentHP = 0
                hero.isDead = true
                println()
            } else {
                println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
                println()
            }
        }
        Thread.sleep(SLEEP_TIME)
    }
}
