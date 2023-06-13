package Helden

class Warrior(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {

    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
    override var currentHP = maxHP
    override var resurse: Int = 0
    override var maxResurse: Int = 100
    var dmgNwe = 20 * startLevel
    override var numberOfHits = 3

    val attacke = mutableMapOf<String, Int>(
        "Hieb" to dmgNwe * 2,
        "Raserei" to dmgNwe * 3,
        "kkk" to dmgNwe
    )

    init {
        println("Bitte geben sie den Namen für Ihren Krieger ein")
        this.name = readln()
    }
}
