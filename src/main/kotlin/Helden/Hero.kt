package Helden

import Gegner.Opponent
import SLEEP_TIME
import bag
import chars
import deadChars
import enemys
import potion
import scroll1
import scroll2


// klasse für helden
open class Hero(var name: String, var hp: Int = 100, var level: Int = 5, var dmg: Int) {

    var LEVEL = 5
    var HP = 100
    var STARTDMG = 50

    open var startLevel = (5 until 10).random()
    open var resurce: Int = 0
    open var maxResource: Int = 100
    open val maxHP = level * hp
    open var currentHP = maxHP
    open var attacke = mutableMapOf<String, Int>()
    private var critChanze = 50
    open var classPlayer = ""
    open var specialAttackCost: Int? = null
    open var thisResource = ""
    open var aoeCost: Int? = null
    open var rescueRecovery: Int? = null
    open var isDead: Boolean = false
    open var hasBuff: Boolean = false
    open var hasDebuff: Boolean = false

    open fun attackSelection(attack: Map<String, Int>) {}

    private fun standardAttack(target: Opponent, attack: Map<String, Int>, a: Int, b: Int) {

        var atkNamen = attack.keys
        var attacke = atkNamen.elementAt(0)
        val resourceNew = this.resurce + this.rescueRecovery!!
        var crit = (1..100).random()

        if (resourceNew > this.maxResource) {
            this.resurce = this.maxResource
        } else {
            this.resurce += this.rescueRecovery!!
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
            println("'${this.name}' greift '${target.name}' mit '$attacke' an, der ${attack[attacke]} verursacht.")
            println()
            println("--- ${target.name} erleidet ${attack[attacke]} schaden---")
        }

        if (target.hp <= 0) {
            println("${target.name} wurde besiegt")
            println()
            enemys.remove(target)
        }
        println()
        Thread.sleep(SLEEP_TIME)

    }

    open fun spezialAttack(target: Opponent, player: MutableList<Hero>, attack: Map<String, Int>, a: Int, b: Int) {}

    private fun aoeDmgHero(attack: Map<String, Int>, a: Int, b: Int) {

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
        this.resurce -= this.aoeCost!!
    }

    open fun attack(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        this.resurce = a
        this.maxResource = b
        this.level = startLevel

        if (this.isDead) {
            println("${this.name} ist tot und kann nicht angreifen.")
            return
        }

        println("--- ${this.classPlayer} ist an der Reihe ---")
        this.attackSelection(attack)

        var eingabe: Int? = null
        do {

            try {
                eingabe = readln().toInt()

                when (eingabe) {

                    1 -> {
                        this.standardAttack(target, attack, a, b)
                        break
                    }

                    2 -> {
                        if (a >= this.specialAttackCost!!) {
                            this.spezialAttack(target, chars, attack, a, b)
                            break
                        } else {
                            println("Sie haben nicht genug $thisResource")
                            println("Die standert Attacke wird ausgeführt.")
                            this.standardAttack(target, attack, a, b)
                        }
                    }

                    3 -> {
                        if (a >= this.aoeCost!!) {
                            this.aoeDmgHero(attack, a, b)

                        } else {
                            println("Sie haben nicht genug $thisResource")
                            println("Die standert Attacke wird ausgeführt.")
                            this.standardAttack(target, attack, a, b)
                        }
                    }

                    4 -> {
                        inventory(target, attack, a, b)
//                TODO Beutel einfügen
                    }

                }
            } catch (e: Exception) {
                println("Falsche Eingabe: Bitte geben sie eine Zahl zwischen 1 und 4 ein!!")
            }

        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
    }

    open fun inventory(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        println("In Ihrem Inventar sind folgende Gegenstände:")
        println()
        println("0 - Zurück")
        for (i in bag) {
            println("${bag.indexOf(i) + 1} - ${i.name} - Anzahl: ${i.anzahl}")
        }

        println("Welchen gegenstand wollen Sie benutzen?")

        var input: Int? = null

        while (input !in listOf(0, 1, 2, 3)) {
            try {
                input = readln().toInt()

            } catch (e: NumberFormatException) {
                println("Ungültige Eingabe. Bitte geben Sie 1 oder 2 ein, oder 0 für zurück.")
            }
        }
        if (input == 0) {
            this.attack(target, attack, a, b)
        }
        if (input == 1) {
            healPotion(target, attack, a, b)
        }
        if (input == 2) {
            dmgRole(target, attack, a, b)
        }
        if (input == 3) {
            revivalRole(target, attack, a, b)
        }
    }

    fun healPotion(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        var needPoition = chars.filter { it.currentHP < it.maxHP }

        println("Wer soll den 'Heiltrank' (stellt 50% der max. HP wieder her) bekommen?")
        println("0 - Zurück")
        for (hero in needPoition) {
            if (hero.isDead){
                continue
            } else {
                println("${needPoition.indexOf(hero) + 1} - ${hero.name} - HP: ${hero.currentHP}/${hero.maxHP}")
            }
        }
        if (potion.anzahl == 0) {
            println("Sie haben keine Heiltränke mehr.")
            Thread.sleep(SLEEP_TIME)
            inventory(target, attack, a, b)
        }

        var input: Int? = null

        while (input !in (0..needPoition.size)) {
            try {
                input = readlnOrNull()?.toInt()
                if (input == 0) {
                    inventory(target, attack, a, b)
                }
                if (input != null) {
                    if (input > needPoition.size) {
                        println("Ungültige Eingabe. Bitte geben Sie 1 oder ${needPoition.size} ein, oder 0 für zurück..")
                    } else {
                        var selectedHero = needPoition[input - 1]
                        var maxHP = selectedHero.maxHP
                        var hp = selectedHero.currentHP
                        var heal = selectedHero.maxHP / 2
                        var newHP = hp + heal
                        if (newHP > maxHP) {
                            println("${selectedHero.name} wurde geheilt ${selectedHero.maxHP}/${selectedHero.maxHP}")
                            selectedHero.currentHP = selectedHero.maxHP
                        } else {
                            selectedHero.currentHP += heal
                            println("${selectedHero.name} wurde geheilt ${selectedHero.currentHP}/${selectedHero.maxHP}")
                        }
                        potion.anzahl -= 1
                    }
                }
            } catch (e: NumberFormatException) {
//                        wenn ein String eingegeben wird
                println("Ungültige Eingabe. Bitte geben Sie 1 oder ${needPoition.size} ein.")
            }
        }
        Thread.sleep(SLEEP_TIME)
    }

    fun revivalRole(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {

        var needRevival = deadChars

        println("Wem wollen sie 'Wiederbeleben' mit 50% HP??")
        println("0 - Zurück")
        for (i in needRevival) {
            println("${needRevival.indexOf(i) + 1} - ${i.name} - HP: ${i.currentHP}/${i.maxHP}")
        }
        if (scroll1.anzahl == 0) {
            println("Sie haben keine Rolle der Wiederbelebung mehr.")
            Thread.sleep(SLEEP_TIME)
            inventory(target, attack, a, b)
        }

        var input: Int? = null

        while (input !in (0..needRevival.size)) {
            try {
                input = readlnOrNull()?.toInt()
                if (input == 0) {
                    inventory(target, attack, a, b)
                }
                if (input != null) {
                    if (input > needRevival.size) {
                        println("Ungültige Eingabe. Bitte geben Sie 1 oder ${needRevival.size} ein, oder 0 für zurück..")
                    } else {
                        val selectedHero = needRevival[input - 1]

                        val heal = selectedHero.maxHP / 2
                        selectedHero.currentHP += heal

                        println("${selectedHero.name} wurde wiederbelebt ${selectedHero.currentHP}/${selectedHero.maxHP}")
//                        chars.add(selectedHero)
                        selectedHero.isDead = false
                        deadChars.remove(selectedHero)
                        scroll1.anzahl -= 1
                        break
                    }
                }
            } catch (e: NumberFormatException) {
//                        wenn ein String eingegeben wird
                println("Ungültige Eingabe. Bitte geben Sie 1 oder ${needRevival.size} ein.")
            }
        }
        Thread.sleep(SLEEP_TIME)

    }

    fun dmgRole(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {


        println("Wem wollen sie die 'Rolle des Doppelten Schadens' bekommen??")
        println("0 - Zurück")
        for (char in chars) {
            if (char.isDead){
                continue
            } else {
                println("${chars.indexOf(char) + 1} - ${char.name} - HP: ${char.currentHP}/${char.maxHP}")
            }
        }
        if (scroll2.anzahl == 0) {
            println("Sie haben keine 'Rolle des Doppelten Schadens' mehr.")
            Thread.sleep(SLEEP_TIME)
            inventory(target, attack, a, b)
        }

        var input: Int? = null

        while (input !in (0..chars.size)) {
            try {
                input = readlnOrNull()?.toInt()
                if (input == 0) {
                    inventory(target, attack, a, b)
                }
                if (input != null) {
                    if (input > chars.size) {
                        println("Ungültige Eingabe. Bitte geben Sie 1 oder ${chars.size} ein, oder 0 für zurück..")
                    } else {
                        val selectedHero = chars[input - 1]
                        selectedHero.hasBuff = true

                        println("${selectedHero.name} wurde gebufft und macht mit seinen nächsten Angriffen doppelten Schaden")
                        println()
                        scroll2.anzahl -= 1
                        break
                    }
                }
            } catch (e: NumberFormatException) {
//                        wenn ein String eingegeben wird
                println("Ungültige Eingabe. Bitte geben Sie 1 oder ${chars.size} ein.")
            }
        }
        Thread.sleep(SLEEP_TIME)

    }


// TODO funktionen zum heilen einfügen!!!!!!
}







