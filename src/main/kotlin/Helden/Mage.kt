package Helden

class Mage(level: Int, hp: Int) : Hero(hp, level) {


    var startLevel = (5 until 10).random()
    var name: String = ""
    var maxHP: Int = hp * startLevel
    var startHP = maxHP
    private var currHP: Int = hp
    var mana: Int = 0
    var maxMana: Int = 100


    init {
        println("Bitte geben sie den Namen für Ihren Magier ein")
        this.name = readln()


    }
}
