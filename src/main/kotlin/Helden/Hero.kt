package Helden

import Gegner.Opponent
import Inventory.Healpotion
import Inventory.Item
import Inventory.Scroll
import chars


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
    open var critChanze = 50


    open fun attack(target: Opponent, attack: MutableMap<String, Int>, a: Int, b: Int) {}

    open fun inventory() {

        var healpotion = Healpotion("Heiltrank", 3)
        var scroll = Scroll("Wiederbelebungs-Rolle",1)
        println("In Ihrem Inventar sind folgende Gegenstände:")
        println()
        println("1 - ${healpotion.name} - Anzahl ${healpotion.anzahl}, stellt 50% HP wieder her.")
        println("2 - ${scroll.name} - Anzahl ${scroll.anzahl}, belebt einen Helden wieder")

        var input: Int? = null

        while (input !in listOf(1, 2)) {
            try {
                input = readlnOrNull()?.toInt()

            } catch (e: NumberFormatException) {
                println("Ungültige Eingabe. Bitte geben Sie 1 oder 2 ein.")
            }
        }
        if (input == 1){
            var needPoition = chars.filter { it.currentHP < it.maxHP }

            }

        }
    }







