package Helden

import Gegner.Opponent
import SLEEP_TIME
import enemys

class Warrior(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {

    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    override var resurce: Int = 40
    override var maxResource: Int = 100
    var dmgNwe = 20 * startLevel
    var numberOfHits = 3
    override var thisResource = "Wut"
    override var classPlayer = "Krieger"
    override var rescueRecovery: Int? = 20
    override var specialAttackCost: Int? = 40
    override var aoeCost: Int? = 20


    override var attacke = mutableMapOf<String, Int>(
        "Hieb" to dmgNwe * 2,
        "Raserei" to dmgNwe * 3,
        "Wirbelwind" to dmgNwe
    )

//    init {
//        println("Bitte geben sie den Namen für Ihren Krieger ein")
//        this.name = readln()
//    }

     override fun attackSelection(attack: Map<String, Int>) {
        var atkNamen = attack.keys
        var atkDmg = attack.values
        println("Sie haben folgende Optionen")
        println("1 - ${atkNamen.elementAt(0)}: Macht ${atkDmg.elementAt(0)} schaden und stellt ${this.rescueRecovery} ${this.thisResource} her.")
        println("2 - ${atkNamen.elementAt(1)}: Führt $numberOfHits angriffe durch die pro schlag ${atkDmg.elementAt(1)} schaden anrichten und kostet ${this.specialAttackCost} ${this.thisResource}.")
        println("3 - ${atkNamen.elementAt(2)}: Macht ${atkDmg.elementAt(2)} schaden an allen Gegnern, und kostet ${this.rescueRecovery} ${this.thisResource}.")
        println("4 - Inventar")
    }

    override fun spezialAttack(target: Opponent, player: MutableList<Hero>, attack: Map<String, Int>,a: Int, b: Int) {

        val atkNamen = attack.keys.toList()
        val attacke = atkNamen.elementAt(1)
        val damage = numberOfHits * attack[attacke]!!

        this.resurce -= specialAttackCost!!
        println("'${this.name}' greift '${target.name}' mit '$attacke' an")
        println()
        println("--- ${target.name} erleidet $damage Schaden ---")
        println()
        target.hp -= damage
        Thread.sleep(SLEEP_TIME / 2)
        if (target.hp <= 0) {
            println("${target.name} wurde besiegt")
            enemys.remove(target)
        }
        this.resurce -= this.specialAttackCost!!
        Thread.sleep(SLEEP_TIME)
    }
}
