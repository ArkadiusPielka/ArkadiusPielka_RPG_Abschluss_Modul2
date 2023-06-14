package Gegner

import bossHelper
import enemy



class Boss(name: String, hp: Int) : Opponent(name, hp) {

    var bossHP = 10000
    var bossMaxHP = 10000

//    var bossHelper =BossHelper("Skelett - Krieger", 3000)


    var bossAtk = mutableMapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300,
        "Beschwören" to 0
    )


    override fun spezialAttackBoss() {
        var atkNamen = bossAtk.keys
        var attacke = atkNamen.elementAt(2)

        if (attacke == "Beschwören") {
            println("'${this.name}' führt eine '$attacke' durch.")
            bossHelper()
            var bossHelper = BossHelper("Skelett - Krieger", 3000)
            enemy.add(bossHelper)
            bossAtk.remove(attacke)

        }
    }
}


