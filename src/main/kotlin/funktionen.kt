import Gegner.Boss
import Helden.Mage
import Helden.Monk
import Helden.Warrior

// Helden werden Generiert
var level = 5
var hp = 100

var warrior = Warrior(level, hp)
var mage = Mage(level, hp)
var monk = Monk(level, hp)

//Der Boss wird erstellt
var bossName = "Der Dunkle Ritter"
var bossMaxHP = 10000
var boss = Boss(bossName, bossMaxHP)

// Helden werden in der Konsole ausgegeben
fun heldenErstellen() {


    println("--- Klasse Krieger ---")
    println("Name: ${warrior.name}\tLevel: ${warrior.startLevel}")
    println("HP: ${warrior.startHP}/${warrior.maxHP}\tWut: ${warrior.rage}/${warrior.maxRage}")
    println()
    Thread.sleep(SLEEP_TIME)

    println("--- Klasse Magier ---")
    println("Name: ${mage.name}\tLevel: ${mage.startLevel}")
    println("HP: ${mage.startHP}/${mage.maxHP}\tMana: ${mage.mana}/${mage.maxMana}")
    println()
    Thread.sleep(SLEEP_TIME)

    println("--- Klasse Mönch ---")
    println("Name: ${monk.name}\tLevel: ${monk.startLevel}")
    println("HP: ${monk.startHP}/${monk.maxHP}\tKombo: ${monk.kombo}/${monk.maxKombo}")
    Thread.sleep(SLEEP_TIME)
}

//Boss wird in der konsole ausgegeben
fun bossErstellen() {

    println("Der Boss wird erstellt:")
    Thread.sleep(SLEEP_TIME)
    println("--- $bossName ---")
    println("Name: ${boss.name}\t")
    println("HP: ${boss.bossHP}/${boss.bossMaxHP}")
    println()
}

