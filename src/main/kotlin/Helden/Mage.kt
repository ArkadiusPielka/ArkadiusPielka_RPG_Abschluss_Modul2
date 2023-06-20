package Helden

import Gegner.Opponent
import SLEEP_TIME
import chars
import enemys


class Mage(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {


    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    override var maxResource: Int = 20 * startLevel
    override var resurce: Int = maxResource
    var dmgNwe = 20 * startLevel
    override var thisResource = "Mana"
    override var classPlayer = "Magier"
    override var rescueRecovery: Int? = 20
    override var specialAttackCost: Int? = maxResource - 20
    override var aoeCost: Int? = 20

    override var attacke = mutableMapOf<String, Int>(
        "Feuerball" to dmgNwe * 2,
        "Vitaga" to maxHP / 2,
        "Meteor" to dmgNwe
    )

//    init {
//        println("Bitte geben sie den Namen für Ihren Magier ein")
//        this.name = readln()
//    }

    override fun attackSelection(attack: Map<String, Int>) {
        var atkNamen = attack.keys
        var atkDmg = attack.values
        println("Sie haben folgende Optionen")
        println("1 - ${atkNamen.elementAt(0)}: Macht ${atkDmg.elementAt(0)} schaden und stellt ${this.rescueRecovery} ${this.thisResource} her.")
        println("2 - ${atkNamen.elementAt(1)}: Heilt alle Helden um ${atkDmg.elementAt(1)} HP und kostet ${this.specialAttackCost} ${this.thisResource}.")
        println("3 - ${atkNamen.elementAt(2)}: Macht ${atkDmg.elementAt(2)} schaden an allen Gegnern, und kostet ${this.rescueRecovery} ${this.thisResource}.")
        println("4 - Beutel")
    }

    override fun spezialAttack(target: Opponent, player: MutableList<Hero>, attack: Map<String, Int>,a: Int, b: Int) {

// TODO heilung noch anpassen

        val atkNamen = attack.keys
        val attacke = atkNamen.elementAt(1)
        val healing = attack[attacke]!!

        println("'${this.name}' Heilt alle mit '$attacke':")

        var healNeeded: Boolean? = null
        for (char in player) {
            if (healNeeded != null || healNeeded == false) {
                println("Es braucht keiner eine Heilung")
            }
            if (char.currentHP <= 0) {
                println("${char.name} ist besiegt und kann nicht geheilt werden ${char.maxHP}/${char.maxHP}")
            } else if (char.currentHP < char.maxHP) {
                val life = char.currentHP
                val newHP = healing + life
                if (newHP > char.maxHP) {
                    println("${char.name} wurde geheilt ${char.maxHP}/${char.maxHP}")
                    char.currentHP = char.maxHP
                } else {
                    char.currentHP += healing
                    println("${char.name} wurde geheilt ${char.currentHP}/${char.maxHP}")
                    healNeeded = true
                }
            }
            this.resurce -= this.specialAttackCost!!
            println()
            Thread.sleep(SLEEP_TIME)
        }
    }
}


