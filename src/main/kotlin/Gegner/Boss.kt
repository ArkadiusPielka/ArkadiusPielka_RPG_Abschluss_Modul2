package Gegner

import Helden.Hero
import mage
import monk
import warrior

class Boss(name: String, hp: Int): Opponent(name, hp) {

    var bossName = ""
    var bossHP = 10000
    var bossMaxHP = 10000


    var bossAtk = mapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300
    )
    private var chars = mutableListOf(warrior,mage, monk)

fun bossAtk() {
    var atkNamen = bossAtk.keys.toList().random()
    var atk = atkNamen
    var hero = chars.random()
    println("'${this.name}' greift mit $atk an.")
    if(atkNamen == "Feueratem") {
        println("'${this.name}' greift alle mit '$atk' an")
        warrior.currentHP -= bossAtk[atk]!!
        monk.currentHP -= bossAtk[atk]!!
        mage.currentHP -= bossAtk[atk]!!
        println("${warrior.name} hat noch ${warrior.currentHP}/${warrior.maxHP}")
        println("${monk.name} hat noch ${monk.currentHP}/${monk.maxHP}")
        println("${mage.name} hat noch ${mage.currentHP}/${mage.maxHP}")
        println()
    } else {
        println("'${this.name}' greift '${hero.name}' mit '$atk' an")
        hero.currentHP -= bossAtk[atk]!!
        println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
        println()
    }
    if (hero.currentHP <= 0){
        chars.remove(hero)
    }

}
}