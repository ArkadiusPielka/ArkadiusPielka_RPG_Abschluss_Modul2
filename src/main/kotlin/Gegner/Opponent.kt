package Gegner

import Helden.Hero
import chars


// klasse für gegner
open class Opponent(var name: String, var hp: Int) {

    val maxHP = hp

    open fun spezialAttackBoss() {

    }

    fun bossAtk(target: MutableList<Hero>, attack: Map<String, Int>) {
        var atkNamen = attack.keys.toList().random()
        var hero = chars.random()

        if (atkNamen == "Feueratem") {
            println("'${this.name}' greift alle mit '$atkNamen' an")
            for (char in target) {
                char.currentHP -= attack[atkNamen]!!

                println("${char.name} hat noch ${char.currentHP}/${char.maxHP}")
            }
            println()
        } else if (atkNamen == "Beschwören") {
            this.spezialAttackBoss()
        }
        else {
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