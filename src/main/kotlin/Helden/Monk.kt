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
    override var thisResource = "Chakra"
    override var classPlayer = "Mönch"
    override var rescueRecovery: Int? = 2
    override var specialAttackCost: Int? = 4
    override var aoeCost: Int? = 4


    override var attacke = mutableMapOf(
        "Faustschlag" to dmgNwe * 2,
        "Todes-Stoß" to dmgNwe,
        "Bodenschlag" to dmgNwe
    )

//    init {
//        println("Bitte geben sie den Namen für Ihren Mönch ein")
//        this.name = readln()
//    }

    override fun attackSelection(attack: Map<String, Int>) {
        var atkNamen = attack.keys
        var atkDmg = attack.values
        println("Sie haben folgende Optionen")
        println("1 - ${atkNamen.elementAt(0)}: Macht ${atkDmg.elementAt(0)} schaden und stellt ${this.rescueRecovery} ${this.thisResource} her.")
        println("2 - ${atkNamen.elementAt(1)}: Führt einen angriff aus der ${atkDmg.elementAt(2)} schaden verursacht. Wenn die HP des Ziels unter 30% = Ziel wird getötet, Kosten: ${this.specialAttackCost} ${this.thisResource}.")
        println("3 - ${atkNamen.elementAt(2)}: Macht ${atkDmg.elementAt(2)} schaden an allen Gegnern, und kostet ${this.rescueRecovery} ${this.thisResource}.")
        println("4 - Beutel")
    }

    override fun spezialAttack(target: Opponent, player: MutableList<Hero>, attack: Map<String, Int>,a: Int, b: Int) {

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
        this.resurce -= this.specialAttackCost!!
        Thread.sleep(SLEEP_TIME / 2)
    }
}





