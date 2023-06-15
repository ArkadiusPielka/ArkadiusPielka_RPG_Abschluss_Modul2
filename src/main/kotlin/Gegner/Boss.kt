package Gegner

import Helden.Hero
import bossHelper
import enemys


class Boss(name: String, hp: Int, maxHP: Int) : Opponent(name, hp, maxHP) {


    var attack = mutableMapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300,
        "Beschwören" to 0
    )

    override fun aoeDamage(target: MutableList<Hero>, attack: Map<String, Int>) {
        var atkNamen = "Feueratem"
        println("'${this.name}' greift alle mit '$atkNamen' an")
        for (char in target) {
                char.currentHP -= attack[atkNamen]!!

                println("${char.name} hat noch ${char.currentHP}/${char.maxHP}")
            }
        }


    override fun spezialAttackBoss() {
        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(2)

        if (attacke == "Beschwören") {
            println("'${this.name}' führt eine '$attacke' durch.")
            bossHelper()
            var bossHelper = BossHelper("Skelett - Krieger", 3000, 3000)
            enemys.add(bossHelper)
            attack.remove(attacke)

        }
    }
}


