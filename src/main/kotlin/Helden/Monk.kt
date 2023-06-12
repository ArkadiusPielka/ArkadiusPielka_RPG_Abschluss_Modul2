package Helden

class Monk(level: Int, hp: Int) : Hero(hp, level) {


    var startLevel = (5 until 10).random()
    var name: String = ""
    var maxHP: Int = hp * startLevel
    var startHP = maxHP
    private var currHP: Int = hp
    var kombo: Int = 0
    var maxKombo: Int = 100


    init {
        println("Bitte geben sie den Namen für Ihren Mönch ein")
        this.name = readln()

    }
}
