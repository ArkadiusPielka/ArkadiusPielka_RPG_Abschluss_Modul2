package Helden

import Gegner.Boss
import Gegner.BossHelper
import Gegner.Opponent
import SLEEP_TIME
import enemys

class Warrior(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {

    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    override var resurse: Int = 0
    override var maxResurse: Int = 100
    var dmgNwe = 20 * startLevel
    var numberOfHits = 3
    override var thisResurse = "Wut"
    override var classPlayer = "Krieger"
    override var resuceRecovery = 20
    override var specialAttackCost = 40

    override var attacke = mutableMapOf<String, Int>(
        "Hieb" to dmgNwe * 2,
        "Raserei" to dmgNwe * 3,
        "Wirbelwind" to dmgNwe
    )

    init {
        println("Bitte geben sie den Namen für Ihren Krieger ein")
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
        var numberOfHits = 3

        if (a >= specialAttackCost) {
            for (i in 1..numberOfHits) {
                val damage = attack[attacke]!!
                target.hp -= damage
            }
            this.resurse -= specialAttackCost
            println("'${this.name}' greift '${target.name}' mit '$attacke' an")
            println("--- ${target.name} erleidet ${attack[attacke]?.times(numberOfHits)} schaden---")
            Thread.sleep(SLEEP_TIME / 2)
            println("Name: ${target.name}\t")
            println("HP: ${target.hp}/${target.maxHP}")
            println()
        } else {
            var attackeFehlschlag = atkNamen.elementAt(0)
            println("Sie haben nicht genug von ihrer resurse")
            println("Es wurde ${attackeFehlschlag} gewählt.")
            println("'${this.name}' greift '${target.name}' mit '${attackeFehlschlag}' an")
            target.hp -= attack[attackeFehlschlag]!!
            this.resurse += this.resuceRecovery
            println()
            println("--- ${target.name} erleidet ${attack[attackeFehlschlag]} schaden---")
            Thread.sleep(SLEEP_TIME / 2)
            println("Name: ${target.name}\t")
            println("HP: ${target.hp}/${target.maxHP}")

        }
    }

    override fun aoeDmgHero(target: MutableList<Opponent>, attack: Map<String, Int>, a: Int, b: Int) {

            var atkNamen = "Wirbelwind"
            println("'${this.name}' greift alle mit '$atkNamen' an")
            for (enemy in target) {
                enemy.hp -= attack[atkNamen]!!
                println("${enemy.name} hat noch ${enemy.hp}/${enemy.maxHP}")
                break
            }
    }
}
