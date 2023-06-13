import Gegner.Boss
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


// Helden werden in der Konsole ausgegeben
fun heldenErstellen() {

    println("--- Klasse Krieger ---")
    println("Name: ${warrior.name}\tLevel: ${warrior.startLevel}")
    println("HP: ${warrior.currentHP}/${warrior.maxHP}\tWut: ${warrior.resurse}/${warrior.maxResurse}")
    println()
    Thread.sleep(SLEEP_TIME / 2)

    println("--- Klasse Magier ---")
    println("Name: ${mage.name}\tLevel: ${mage.startLevel}")
    println("HP: ${mage.startHP}/${mage.maxHP}\tMana: ${mage.mana}/${mage.maxMana}")
    println()
    Thread.sleep(SLEEP_TIME / 2)

    println("--- Klasse Mönch ---")
    println("Name: ${monk.name}\tLevel: ${monk.startLevel}")
    println("HP: ${monk.startHP}/${monk.maxHP}\tKombo: ${monk.kombo}/${monk.maxKombo}")
    Thread.sleep(SLEEP_TIME / 2)
}

//Boss wird in der konsole ausgegeben
fun bossErstellen() {

    println("Der Boss wird erstellt:")
    Thread.sleep(SLEEP_TIME / 2)
    println("--- $bossName ---")
    println("Name: ${boss.name}\t")
    println("HP: ${boss.bossHP}/${boss.bossMaxHP}")
    println()
}



