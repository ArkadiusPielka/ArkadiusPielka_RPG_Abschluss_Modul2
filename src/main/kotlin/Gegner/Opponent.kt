package Gegner

import Helden.Hero
import SLEEP_TIME
import boss
import bossHelper
import chars


// klasse für gegner
open class Opponent(var name: String, var hp: Int, var maxHP: Int) {


    open var attack = mutableMapOf<String, Int>()

    open fun attack(target: MutableList<Hero>, attack: Map<String, Int>) {}

}