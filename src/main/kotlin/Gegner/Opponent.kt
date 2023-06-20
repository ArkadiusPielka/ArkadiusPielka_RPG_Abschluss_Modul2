package Gegner

import Helden.Hero


// klasse für gegner
open class Opponent(var name: String, var hp: Int, var maxHP: Int) {


    open var attack = mutableMapOf<String, Int>()

    open fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {}

}