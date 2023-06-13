package Helden

import Gegner.Boss


// klasse für helden
open class Hero(var name: String, var hp: Int = 100, var level: Int = 5, var dmg: Int) {


    open val maxHP: Int = 100
    open var currentHP: Int = hp - dmg

    open fun attack(target: Boss) {
    println("$name greift ${target.name} an und macht $dmg schaden")
    target.hp -= dmg
}

}



