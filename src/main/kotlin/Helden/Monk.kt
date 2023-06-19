package Helden

import Gegner.Opponent
import SLEEP_TIME
import enemys


class Monk(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {


    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    override var resurce: Int = 6
    override var maxResource: Int = 6
    var dmgNwe = 20 * startLevel
    var thisResource = "Chakra"
    var classPlayer = "Mönch"
    var rescueRecovery = 2
    var specialAttackCost = 4
    var aoeCost = 4


    override var attacke = mutableMapOf(
        "Faustschlag" to dmgNwe * 2,
        "Todes-Stoß" to dmgNwe,
        "Bodenschlag" to dmgNwe
    )

//    init {
//        println("Bitte geben sie den Namen für Ihren Mönch ein")
//        this.name = readln()
//    }

    fun attackSelection(attack: Map<String, Int>) {
        var atkNamen = attack.keys
        var atkDmg = attack.values
        println("Sie haben folgende Optionen")
        println("1 - ${atkNamen.elementAt(0)}: Macht ${atkDmg.elementAt(0)} schaden und stellt ${this.rescueRecovery} ${this.thisResource} her.")
        println("2 - ${atkNamen.elementAt(1)}: Führt einen angriff aus der ${atkDmg.elementAt(2)} schaden verursacht. Wenn die HP des Ziels unter 30% = Ziel wird getötet, Kosten: ${this.specialAttackCost} ${this.thisResource}.")
        println("3 - ${atkNamen.elementAt(2)}: Macht ${atkDmg.elementAt(2)} schaden an allen Gegnern, und kostet ${this.rescueRecovery} ${this.thisResource}.")
        println("4 - Beutel")
    }

    fun standartAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(0)
        val resourceNew = this.resurce + this.rescueRecovery
        var crit = (1..100).random()

        if (resourceNew > this.maxResource) {
            this.resurce = this.maxResource
        } else {
            this.resurce += this.rescueRecovery
        }

        if (crit <= critChanze) {
            var damage = attack[attacke]!! * 2
            target.hp -= damage
            println("Sie haben $attacke gewählt.")
            println("'${this.name}' greift '${target.name}' mit '$attacke' an, der ${attack[attacke]} verursacht.")
            println()
            println("--- ${target.name} wird kritischen getroffen und bekommt doppelten schaden $damage ---")
        } else {
            target.hp -= attack[attacke]!!
            println("Sie haben $attacke gewählt.")
            println("'${this.name}' greift '${target.name}' mit '$attacke' an")
            println()
            println("--- ${target.name} erleidet ${attack[attacke]} schaden---")
        }

        if (target.hp <= 0) {
            println("${target.name} wurde besiegt")
            println()
            enemys.remove(target)
        }
        Thread.sleep(SLEEP_TIME)

    }

    fun spezialAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(1)

        println("Sie haben $attacke gewählt.")
        println("'${this.name}' greift '${target.name}' mit '$attacke' an")

        if (target.hp <= target.maxHP * 0.3) {
            println()
            println("'${target.name}' wurde getötet.")
            println()
            target.hp = 0
            enemys.remove(target)
        } else {
            println("Ziel HP über 30%: '${this.name}' greift '${target.name}' mit '$attacke' an und richtet ${attack[attacke]} schaden.")
            println("Name: ${target.name}\t")
            println("HP: ${target.hp}/${target.maxHP}")
            if (target.hp <= 0) {
                println()
                println("${target.name} wurde besiegt")
                println()
                enemys.remove(target)
            }
        }
        Thread.sleep(SLEEP_TIME / 2)
    }

    fun aoeDmgHero(attack: Map<String, Int>, a: Int, b: Int) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(2)
        var deadInFight = mutableListOf<Opponent>()
        println()
        println("'${this.name}' greift mit '${attacke}' an und richtet ${attack[attacke]} schaden an allen gegnern an.")
        println()
        for (enemy in enemys) {
            val crit = (1..100).random()
            if (crit <= critChanze) {
                val damage = attack[attacke]!! * 2
                enemy.hp -= damage
                println("--- ${enemy.name} erleidet doppelten schade ${damage} ---")
                if (enemy.hp <= 0) {
                    println()
                    println("${enemy.name} wurde besiegt")
                    println()
                    deadInFight.add(enemy)
                }

            } else {
                enemy.hp -= attack[attacke]!!
                println("--- ${enemy.name} erleidet ${attack[attacke]} schaden---")
                if (enemy.hp <= 0) {
                    println()
                    println("${enemy.name} wurde besiegt")
                    println()
                    deadInFight.add(enemy)
                }

            }
        }
        enemys.removeAll(deadInFight)
        println()
        Thread.sleep(SLEEP_TIME)
        this.resurce -= aoeCost
    }

    override fun attack(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        this.resurce = a
        this.maxResource = b
        this.level = startLevel

        println("--- ${this.classPlayer} ist an der Reihe ---")
        this.attackSelection(attack)

        var eingabe: Int? = null
        do {

            try {
                eingabe = readln().toInt()

                when (eingabe) {

                    1 -> {
                        this.standartAttack(target, attack, a, b)
                        break
                    }

                    2 -> {
                        if (a >= specialAttackCost) {
                            this.spezialAttack(target, attack, a, b)
                            break
                        } else {
                            println("Sie haben nicht genug $thisResource")
                            println("Die standert Attacke wird ausgeführt.")
                            this.standartAttack(target, attack, a, b)
                        }
                    }

                    3 -> {
                        if (a >= aoeCost) {
                            this.aoeDmgHero(attack, a, b)

                        } else {
                            println("Sie haben nicht genug $thisResource")
                            println("Die standert Attacke wird ausgeführt.")
                            this.standartAttack(target, attack, a, b)
                        }
                    }

                    4 -> {
//                TODO Beutel einfügen
                    }

                }
            } catch (e: Exception) {
                println("Falsche Eingabe: Bitte geben sie eine Zahl zwischen 1 und 4 ein!!")
            }

        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
    }
}





