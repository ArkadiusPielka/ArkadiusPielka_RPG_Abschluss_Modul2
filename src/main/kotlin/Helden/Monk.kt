package Helden

class Monk(name: String, level: Int, hp: Int, dmg: Int) : Hero(name, hp, level, dmg) {


//    override var startLevel = (5 until 10).random()
    override var maxHP: Int = hp * startLevel
     override var currentHP = maxHP
    var startHP = maxHP
    override var resurse: Int = 0
    override var maxResurse: Int = 6
    var dmgNwe = 20 * startLevel
    override var numberOfHits = 3

    val attacke = mutableMapOf<String, Int>(
        "Faustschlag" to dmgNwe * 2,
        "Tritt" to dmgNwe,
        "Bodenschlag" to dmgNwe
    )

    init {
        println("Bitte geben sie den Namen für Ihren Mönch ein")
        this.name = readln()
    }


//    fun monkAtk() {
//        var atkNamen = monkAtk.keys
//        println("--- Mönch ist an der Reihe ---")
//        println("Name: ${this.name}\tLevel: ${this.startLevel}")
//        println("HP: ${this.currentHP}/${this.maxHP}\tWut: ${this.kombo}/${this.maxKombo}")
//
//        var eingabe: Int = 0
//        do {
//            println()
//            println("Sie haben folgende Optionen")
//            println("1 - ${atkNamen.elementAt(0)}")
//            println("2 - ${atkNamen.elementAt(1)}")
//            println("3 - ${atkNamen.elementAt(2)}")
//            println("4 - Beutel")
//
//            try {
//                eingabe = readln().toInt()
//                var index = eingabe - 1
//
//                var attacke = atkNamen.elementAt(index)
//                when (eingabe) {
//
//                    1 -> {
//                        println("Sie haben $attacke gewählt.")
//                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
//                        boss.bossHP -= monkAtk[attacke]!!
//                        println()
//                    }
//
//                    2 -> {
//                        println("Sie haben $attacke gewählt.")
//                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
//                        boss.bossHP -= monkAtk[attacke]!!
//                        println()
//                    }
//
//                    3 -> {
//                        println("Sie haben $attacke gewählt.")
//                        println("'${this.name}' greift '${boss.name}' mit '$attacke' an")
//                        boss.bossHP -= monkAtk[attacke]!!
//                        println()
//                    }
//
//                    4 -> {
////                TODO Beutel einfügen
//                    }
//
//                    else -> {
//                        println("Falsche Eingabe")
//                    }
//
//                }
//
//                println("--- ${boss.name} erleide ${monkAtk[attacke]} schaden---")
//                Thread.sleep(SLEEP_TIME / 2)
//                println("Name: ${boss.name}\t")
//                println("HP: ${boss.bossHP}/${boss.bossMaxHP}")
//
//            } catch (e: Exception) {
//                println("Falsche Eingabe")
//            }
//
//        } while (eingabe != 1 && eingabe != 2 && eingabe != 3 && eingabe != 4)
//    }
}





