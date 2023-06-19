package Gegner

import Helden.Hero
import SLEEP_TIME
import bossHelper
import chars
import deadChars
import enemys


class Boss(name: String, hp: Int, maxHP: Int) : Opponent(name, hp, maxHP) {


    override var attack = mutableMapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300,
        "Beschwören" to 0
    )

    fun aoeDamage(target: MutableList<Hero>, attack: Map<String, Int>) {
        val atkNamen = "Feueratem"
        var deadInFight = mutableListOf<Hero>()
        println("'${this.name}' greift alle mit '$atkNamen' an und richtet ${attack[atkNamen]} schaden an")
        for (char in target) {
            char.currentHP -= attack[atkNamen]!!
            if (char.currentHP <= 0) {
                deadInFight.add(char)
                println("${char.name} wurde besiegt")
                char.currentHP = 0
            } else {
                println("${char.name} hat noch ${char.currentHP}/${char.maxHP}")
            }
        }
        deadChars.addAll(deadInFight)
        chars.removeAll(deadInFight)
    }


    fun spezialAttackBoss(){
        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(2)
        if (attacke == "Beschwören") {
            println("'${this.name}' führt eine '$attacke' durch.")
            println()
            bossHelper()
            enemys.add(bossHelper)
            attack.remove(attacke)
        }
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
            println("'${this.name}' greift '${hero.name}' mit '$atkNamen' und richtet ${attack[atkNamen]} schaden an")
            hero.currentHP -= attack[atkNamen]!!
//            println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
            println()
            if (hero.currentHP <= 0) {
                deadChars.add(hero)
                chars.remove(hero)
                println("${hero.name} wurde besiegt")
                hero.currentHP = 0
                println()
            } else {
                println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
                println()
            }
        }
        Thread.sleep(SLEEP_TIME)
    }
}


