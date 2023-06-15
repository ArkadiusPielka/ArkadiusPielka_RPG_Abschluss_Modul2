package Gegner

import Helden.Hero
import chars


// klasse für gegner
open class Opponent(var name: String, var hp: Int, var maxHP: Int) {


    open fun spezialAttackBoss() {

    }

    open fun aoeDamage(target: MutableList<Hero>, attack: Map<String, Int>) {

    }

    fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {
        var atkNamen = attack.keys.toList().random()
        var hero = chars.random()
        println("--- ${this.name} ist an der Reihe ---")
        if (atkNamen == "Feueratem") {
            this.aoeDamage(target, attack)

            println()
        } else if (atkNamen == "Beschwören") {
            this.spezialAttackBoss()
        } else if (atkNamen == "Stacheln") {
            this.aoeDamage(target, attack)
        } else {
            println("'${this.name}' greift '${hero.name}' mit '$atkNamen' und richtet ${attack[atkNamen]} schaden an")
            hero.currentHP -= attack[atkNamen]!!
            println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
            println()
        }
        if (hero.currentHP <= 0) {
            target.remove(hero)
        }

    }
}