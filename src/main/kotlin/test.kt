import Gegner.Boss

fun main() {

    heldenErstellen()
    bossErstellen()
    warrior.attack(boss, warrior.attacke, warrior.resurse, warrior.maxResurse, 3 )
    monk.attack(boss, monk.attacke, monk.resurse, monk.maxResurse, 3 )
    mage.attack(boss, mage.attacke, mage.resurse, mage.maxResurse, 0)
    warrior.attack(boss, warrior.attacke, warrior.resurse, warrior.maxResurse, 3 )
}