package Gegner



class BossHelper(name: String, hp: Int): Opponent(name, hp) {


    var bossHelperHP = 50000
    var bossHelperMaxHP = 50000


    var bossHelperAtk = mutableMapOf<String, Int>(
        "Feueratem" to 200,
        "Kralle" to 300
    )


}
