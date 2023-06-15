package Helden

import Gegner.Opponent
import SLEEP_TIME


class Monk(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {


    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    override var resurse: Int = 6
    override var maxResurse: Int = 6
    var dmgNwe = 20 * startLevel
    override var thisResurse = "Chakra"
    override var classPlayer = "Mönch"
    override var resuceRecovery = 2
    override var specialAttackCost = 4

    override var attacke = mutableMapOf(
        "Faustschlag" to dmgNwe * 2,
        "Tritt" to dmgNwe,
        "Bodenschlag" to dmgNwe
    )

    init {
        println("Bitte geben sie den Namen für Ihren Mönch ein")
        this.name = readln()
    }

    override fun standartAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int) {
        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(0)
        println("Sie haben $attacke gewählt.")
        println("'${this.name}' greift '${target.name}' mit '$attacke' an")
        target.hp -= attack[attacke]!!
        val resurceNew = this.resurse + this.resuceRecovery
        if (resurceNew > this.maxResurse) {
            this.resurse = this.maxResurse
        } else {
            this.resurse += this.resuceRecovery
        }
            println()
        println("--- ${target.name} erleidet ${attack[attacke]} schaden---")
        println("Name: ${target.name}\t")
        println("HP: ${target.hp}/${target.maxHP}")
        Thread.sleep(SLEEP_TIME)
        println()
    }
    override fun spezialAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(1)
        var duratin = 3

        if (a >= specialAttackCost) {
            println("'${this.name}' greift '${target.name}' mit '$attacke' an die 3 runden lang ${attack[attacke]} schaden verursacht.")
            println("Name: ${target.name}\t")
            println("HP: ${target.hp}/${target.maxHP}")
            var i = duratin
            var round = 1
            while (i > 0) {
                println("Runde $round, '${target.name}' bekommt ${attack[attacke]} Schaden.")
                target.hp -= attack[attacke]!!
                println()
                println("Name: ${target.name}\t")
                println("HP: ${target.hp}/${target.maxHP}")
                i--
                round++
                Thread.sleep(SLEEP_TIME / 2)
            }
        } else {
            var attackeFehlschlag = atkNamen.elementAt(0)
            println("Sie haben nicht genug von ihrer resurse")
            println("Es wurde $attackeFehlschlag gewählt.")
            println("'${this.name}' greift '${target.name}' mit '${attackeFehlschlag}' an")
            target.hp -= attack[attackeFehlschlag]!!
            this.resurse += this.resuceRecovery
            println("--- ${target.name} erleidet ${attack[attackeFehlschlag]} schaden---")
            Thread.sleep(SLEEP_TIME / 2)
            println("Name: ${target.name}\t")
            println("HP: ${target.hp}/${target.maxHP}")
        }
    }


}





