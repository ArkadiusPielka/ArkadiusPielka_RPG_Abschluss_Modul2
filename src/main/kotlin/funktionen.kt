import Gegner.Boss
import Gegner.BossHelper
import Gegner.Opponent
import Helden.Hero
import Helden.Mage
import Helden.Monk

import Helden.Warrior
import Inventory.Healpotion
import Inventory.Item
import Inventory.Scroll

// Helden werden Generiert
var LEVEL = 5
var HP = 100
var STARTDMG = 50

var warrior = Warrior("Akki", LEVEL, HP, STARTDMG)
var mage = Mage("Nico", LEVEL, HP, STARTDMG)
var monk = Monk("Luca", LEVEL, HP, STARTDMG)

var chars: MutableList<Hero> = mutableListOf(warrior, mage, monk)
var deadChars: MutableList<Hero> = mutableListOf()

//Der Boss wird erstellt

var boss = Boss("Der Schwarze Ritter", 10000, 10000)

//Der boss beschwört einen helfer

var bossHelper = BossHelper("Skelett-Krieger", 800, 3000)

var enemys: MutableList<Opponent> = mutableListOf(boss)

var bag: MutableList<Item> = mutableListOf(
    Healpotion("Heiltrank", 3),
    Scroll("Wiederbelebungs-Rolle",1)
)


//Boss wird in der konsole ausgegeben
fun bossErstellen() {


    println()
    Thread.sleep(SLEEP_TIME / 2)
    println("--- ${boss.name} ---")
    println("Name: ${boss.name}\t")
    println("HP: ${boss.hp}/${boss.maxHP}")
    println()
}

fun oneEnemy() {


    if (enemys.size == 2) {
//        Thread.sleep(SLEEP_TIME / 2)
        println("--- Der Boss ---\t\t\t\t --- Der Helfer ---")
        println("--- ${boss.name} ---\t\t --- ${bossHelper.name} ---")
        println("HP: ${boss.hp}/${boss.maxHP}\t\t\t\t\t HP: ${bossHelper.hp}/${bossHelper.maxHP}")
        println()

    } else {
//        Thread.sleep(SLEEP_TIME / 2)
        println("--- ${boss.name} ---")
        println("HP: ${boss.hp}/${boss.maxHP}")
        println()
    }
}


fun bossHelper() {

    Thread.sleep(SLEEP_TIME / 2)
    println("--- ${bossHelper.name} ---")
    println("Name: ${bossHelper.name}\t")
    println("HP: ${bossHelper.hp}/${bossHelper.maxHP}")
    println()
}

fun theFight() {
    println(chars)
    println(deadChars)
    if (enemys.size == 1) {

        for (hero in chars) {
            oneEnemy()
            heldenErstellenNeu()
            if (hero.currentHP > 0) {
                hero.attack(boss, hero.attacke, hero.resurce, hero.maxResource)
            }
        }
        if (boss.hp > 0) {
            boss.attack(chars, boss.attack)
        }
    } else {
        var bossHelperAlive = true
        for (hero in chars) {
            if (enemys.isEmpty()) {
                break
            }
            println()
            oneEnemy()
            heldenErstellenNeu()
            if (bossHelperAlive) {
                println("Wen wollen Sie angreifen? ${hero.name} ")
                println("1 - ${boss.name}: ${boss.hp}/${boss.maxHP}")
                println("2 - ${bossHelper.name}: ${bossHelper.hp}/${bossHelper.maxHP}")
                var input: Int? = null

//                Schleife zur richtigen Eingabe

                while (input !in listOf(1, 2)) {
                    try {
                        input = readlnOrNull()?.toInt()

                    } catch (e: NumberFormatException) {
                        println("Ungültige Eingabe. Bitte geben Sie 1 oder 2 ein.")
                    }
                }
                if (input == 1) {
                    hero.attack(boss, hero.attacke, hero.resurce, hero.maxResource)

                } else {
                    hero.attack(bossHelper, hero.attacke, hero.resurce, hero.maxResource)
                    if (bossHelper.hp <= 0) {
                        bossHelperAlive = false
                    }
                }
            } else {
                if (enemys.isEmpty()) {
                    break
                } else {
                    hero.attack(boss, hero.attacke, hero.resurce, hero.maxResource)
                }
            }
        }
        if (enemys.isEmpty()) {

        } else {
            for (enemy in enemys) {
                if (chars.isEmpty()){
                    break
                } else {
                    enemy.attack(chars, enemy.attack)
                }
            }
        }
    }
}


fun heldenErstellenNeu() {

    println("--- Klasse Krieger ---\t\t\t\t" + "--- Klasse Magier ---\t\t\t\t" + "--- Klasse Mönch ---")
    println("Name: ${warrior.name}\tLevel: ${warrior.startLevel}\t\t\t\t" + "Name: ${mage.name}\tLevel: ${mage.startLevel}\t\t\t\t" + "Name: ${monk.name}\tLevel: ${monk.startLevel}")
    println("HP: ${warrior.currentHP}/${warrior.maxHP}\tWut: ${warrior.resurce}/${warrior.maxResource}\t\t\t\t" + "HP: ${mage.currentHP}/${mage.maxHP}\t Mana: ${mage.resurce}/${mage.maxResource}\t\t\t" + "HP: ${monk.currentHP}/${monk.maxHP}\t Chakra: ${monk.resurce}/${monk.maxResource}")
    println()

}


