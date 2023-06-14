package Helden

import SLEEP_TIME
import boss


class Mage(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {


    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    override var maxResurse: Int = 20 * startLevel
    override var resurse: Int = maxResurse
    var dmgNwe = 20 * startLevel
    override var thisResurse = "Mana"
    override var classPlayer = "Magier"
    override var resuceRecovery = 20
    override var specialAttackCost = 50

    val attacke = mutableMapOf<String, Int>(
        "Feuerball" to dmgNwe * 2,
        "heilung" to 300,
        "Meteor" to dmgNwe
    )

    init {
        println("Bitte geben sie den Namen für Ihren Magier ein")
        this.name = readln()
    }

    override fun spezialAttacOnPlayer(
        player: MutableList<Hero>,
        attack: Map<String, Int>,
        a: Int,
        b: Int,
        numberOfHits: Int
    ) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(1)

        println("'${this.name}' Heilt alle mit '$attacke':")
        if (a >= specialAttackCost) {
            var healNeeded = false
            for (char in player)
                if (char.currentHP < char.maxHP) {
                    var healing = attack[attacke]!!
                    var life = char.currentHP
                    var newHP = healing + life
                    if (newHP > char.maxHP) {
                        println("${char.name} wurde geheilt ${char.maxHP}/${char.maxHP}")
                    } else {
                        char.currentHP = newHP
                        println("${char.name} wurde geheilt ${char.currentHP}/${char.maxHP}")
                        healNeeded = true
                    }

                    }
                    if (!healNeeded) {
                        println("Es braucht keiner eine Heilung")
                }
            this.resurse -= specialAttackCost
            println()

        } else {
            var attackeFehlschlag = atkNamen.elementAt(0)
            println("Sie haben nicht genug von ihrer resurse")
            println("Es wurde $attackeFehlschlag gewählt.")
            val target = boss
            println("'${this.name}' greift '${target.name}' mit '${attackeFehlschlag}' an")
            target.hp -= attack[attackeFehlschlag]!!
            this.resurse += this.resuceRecovery
            println()
            println("--- ${target.name} erleidet ${attack[attackeFehlschlag]} schaden---")
            Thread.sleep(SLEEP_TIME / 2)
            println("Name: ${target.name}\t")
            println("HP: ${target.hp}/${target.maxHP}")
        }
        Thread.sleep(SLEEP_TIME)
    }

}

//for (char in player) {
//                        if (char is Warrior && char.currentHP > hp) {
//                            println("${char.name} wurde geheilt ${char.currentHP}/${char.maxHP}")
//                        } else if (char is Monk && char.currentHP > hp) {
//                            println("${char.name} wurde geheilt ${char.currentHP}/${char.maxHP}")
//                        } else if (char === this && char.currentHP > hp) {
//                            println("${char.name} wurde geheilt ${char.currentHP}/${char.maxHP}")
//                        } else {
//                            char.currentHP += attack[attacke]!!
//                            println("${char.name} wurde geheilt ${char.currentHP}/${char.maxHP}")


