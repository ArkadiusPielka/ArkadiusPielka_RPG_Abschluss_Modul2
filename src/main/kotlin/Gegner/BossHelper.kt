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

    fun aoeDamage(target: MutableList<Hero>, attack: Map<String, Int>) {
        var atkNamen = "Stacheln"
        var deadInFight = mutableListOf<Hero>()
        println("'${this.name}' greift alle mit '$atkNamen' an")
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
        println()
        Thread.sleep(SLEEP_TIME)
    }

    override fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {

        var atkNamen = attack.keys.toList().random()


        println("--- ${this.name} ist an der Reihe ---")
        println()
        Thread.sleep(SLEEP_TIME)

        if (atkNamen == "Stacheln") {
            this.aoeDamage(target, attack)
        } else {
            while (true) {
                var hero = chars.random()
                if (!hero.isDead) {
                    println("'${this.name}' greift '${hero.name}' mit '$atkNamen' und richtet ${attack[atkNamen]} schaden an")
                    hero.currentHP -= attack[atkNamen]!!
                }

                if (hero.currentHP <= 0) {
                    deadChars.add(hero)
//                chars.remove(hero)
                    println("${hero.name} wurde besiegt")
                    hero.currentHP = 0
                    hero.isDead = true
                    println()
                } else {
                    println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
                    println()
                }
                break
            }
        }
    }
}
