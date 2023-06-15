package Gegner

import Helden.Hero


class BossHelper(name: String, hp: Int, maxHP: Int): Opponent(name, hp, maxHP) {



    var attack = mutableMapOf<String, Int>(
        "Schwert-Angriff" to 200,
        "Stacheln" to 100
    )

    override fun aoeDamage(target: MutableList<Hero>, attack: Map<String, Int>) {
        var atkNamen = "Stacheln"
        println("'${this.name}' greift alle mit '$atkNamen' an")
        for (char in target) {
            char.currentHP -= attack[atkNamen]!!

            println("${char.name} hat noch ${char.currentHP}/${char.maxHP}")
        }
    }
}
