import Gegner.Boss
import Gegner.BossHelper
import Gegner.Opponent
import Helden.Mage
import Helden.Monk
import Helden.Warrior

// Helden werden Generiert
var LEVEL = 5
var HP = 100
var STARTDMG = 50

var warrior = Warrior("",LEVEL, HP, STARTDMG)
var mage = Mage("",LEVEL, HP, STARTDMG)
var monk = Monk("",LEVEL, HP, STARTDMG)

var chars = mutableListOf(warrior,mage, monk)
//Der Boss wird erstellt
var bossName = "Der Dunkle Ritter"
var bossMaxHP = 10000
var boss = Boss(bossName, bossMaxHP)

//Der boss beschwört einen helfer
var bossHelperName = "Skelett-Krieger"
var bossHelperMaxHP = 3000
var bossHelper = BossHelper(bossHelperName, bossHelperMaxHP)

var enemy: MutableList<Opponent> = mutableListOf(boss)


// Helden werden in der Konsole ausgegeben
fun heldenErstellen() {

    println("--- Klasse Krieger ---")
    println("Name: ${warrior.name}\tLevel: ${warrior.startLevel}")
    println("HP: ${warrior.currentHP}/${warrior.maxHP}\tWut: ${warrior.resurse}/${warrior.maxResurse}")
    println()
    Thread.sleep(SLEEP_TIME / 2)

    println("--- Klasse Magier ---")
    println("Name: ${mage.name}\tLevel: ${mage.startLevel}")
    println("HP: ${mage.currentHP}/${mage.maxHP}\tMana: ${mage.resurse}/${mage.maxResurse}")
    println()
    Thread.sleep(SLEEP_TIME / 2)

    println("--- Klasse Mönch ---")
    println("Name: ${monk.name}\tLevel: ${monk.startLevel}")
    println("HP: ${monk.currentHP}/${monk.maxHP}\tKombo: ${monk.resurse}/${monk.maxResurse}")
    Thread.sleep(SLEEP_TIME / 2)
}

//Boss wird in der konsole ausgegeben
fun bossErstellen() {

    println("Der Boss wird erstellt:")
    Thread.sleep(SLEEP_TIME / 2)
    println("--- $bossName ---")
    println("Name: ${boss.name}\t")
    println("HP: ${boss.hp}/${boss.bossMaxHP}")
    println()
}

fun bossHelper(){

    Thread.sleep(SLEEP_TIME / 2)
    println("--- $bossHelperName ---")
    println("Name: ${bossHelper.name}\t")
    println("HP: ${bossHelper.bossHelperHP}/${bossHelper.bossHelperMaxHP}")
    println()
}

fun theFight() {

    println("Der Kampf beginnt!!")
    println()
    var i = 0

    if (enemy.size - 1 > 0) {
        println("Wen wollen Sie angreifen?")
        println("1 - ${boss.name}: ${boss.hp}/${boss.maxHP}")
        println("2 - ${bossHelper.name}: ${bossHelper.bossHelperHP}/${bossHelper.bossHelperMaxHP}")
        do {
            println("---- Runde ${i + 1}")
            println()
            if (warrior.hp > 0) {
                warrior.attack(boss, warrior.attacke, warrior.resurse, warrior.maxResurse, 3)
            } else {
                println("${warrior.name} ist tot und kann nicht angreifen.")
            }
            if (monk.hp > 0) {
                monk.attack(boss, monk.attacke, monk.resurse, monk.maxResurse, i)
            } else {
                println("${monk.name} ist tot und kann nicht angreifen.")
            }
            if (mage.hp > 0) {
                mage.attack(boss, mage.attacke, mage.resurse, mage.maxResurse, 0)
            } else {
                println("${mage.name} ist tot und kann nicht angreifen.")
            }
            boss.bossAtk(chars, boss.bossAtk)

            i++
        } while (boss.bossHP >= 0 || (warrior.currentHP >= 0 || mage.currentHP >= 0 || monk.currentHP >= 0))
    }
}



