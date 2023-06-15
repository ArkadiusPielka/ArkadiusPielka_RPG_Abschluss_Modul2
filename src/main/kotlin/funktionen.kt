import Gegner.Boss
import Gegner.BossHelper
import Gegner.Opponent
import Helden.Hero
import Helden.Mage
import Helden.Monk
import Helden.Warrior

// Helden werden Generiert
var LEVEL = 5
var HP = 100
var STARTDMG = 50

var warrior = Warrior("", LEVEL, HP, STARTDMG)
var mage = Mage("", LEVEL, HP, STARTDMG)
var monk = Monk("", LEVEL, HP, STARTDMG)

var chars: MutableList<Hero> = mutableListOf(warrior, mage, monk)

//Der Boss wird erstellt

var boss = Boss("Der Schwarze Ritter", 10000,10000)

//Der boss beschwört einen helfer

var bossHelper = BossHelper("Skelett-Krieger", 3000,3000)

var enemys: MutableList<Opponent> = mutableListOf(boss)


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
    println("--- ${boss.name} ---")
    println("Name: ${boss.name}\t")
    println("HP: ${boss.hp}/${boss.maxHP}")
    println()
}

fun bossHelper() {

    Thread.sleep(SLEEP_TIME / 2)
    println("--- ${bossHelper.name} ---")
    println("Name: ${bossHelper.name}\t")
    println("HP: ${bossHelper.hp}/${bossHelper.maxHP}")
    println()
}


fun theFightNew() {
    println("Der Kampf beginnt!!")
    println()
    var i = 0
    do {
        println("---- Runde ${i + 1} ---")
        println()

        if (enemys.size == 1) {

                for (hero in chars) {

                    if (hero.currentHP > 0) {
                        hero.attack(boss, hero.attacke, hero.resurse, hero.maxResurse)
                        if (boss.hp < 0) {
                            println("Der ${boss.name} wurde besiegt ... Sie haben Gewonnen ")
                            enemys.remove(boss)
                        }
                    }
                }
                    boss.attack(chars, boss.attack)
        }
        else if (enemys.size == 2) {

                for (hero in chars) {
                    println("Wen wollen Sie angreifen?")
                    println("1 - ${boss.name}: ${boss.hp}/${boss.maxHP}")
                    println("2 - ${bossHelper.name}: ${bossHelper.hp}/${bossHelper.maxHP}")
                    val input = readln().toInt()
                    println()
                    if (hero.currentHP > 0 && input == 1) {
                        hero.attack(boss, hero.attacke, hero.resurse, hero.maxResurse)
                        if (boss.hp < 0) {
                            println("Der ${boss.name} wurde besiegt ... ")
                            enemys.remove(boss)
                        }
                    }
                    if (hero.currentHP > 0 && input == 2) {
                        hero.attack(bossHelper, hero.attacke, hero.resurse, hero.maxResurse)
                        if (bossHelper.hp < 0) {
                            println("Der ${bossHelper.name} wurde besiegt ... ")
                            enemys.remove(bossHelper)
                        }
                    }
                }
                boss.attack(chars, boss.attack)
                bossHelper.attack(chars,bossHelper.attack)
        }
        i++
    } while (boss.hp > 0 || (warrior.currentHP > 0 || mage.currentHP > 0 || monk.currentHP > 0))
}

fun heldenErstellenNeu() {
// .padEnd(8, '4')
    println("--- Klasse Krieger ---\t\t\t" + "--- Klasse Magier ---\t\t\t" + "--- Klasse Mönch ---")
    println("Name: ${warrior.name}\tLevel: ${warrior.startLevel}\t\t\t" + "Name: ${mage.name}\tLevel: ${mage.startLevel}\t\t\t" + "Name: ${monk.name}\tLevel: ${monk.startLevel}")
    println("HP: ${warrior.currentHP}/${warrior.maxHP}\tWut: ${warrior.resurse}/${warrior.maxResurse}\t\t\t" + "HP: ${mage.currentHP}/${mage.maxHP}\t Mana: ${mage.resurse}/${mage.maxResurse}\t\t" + "HP: ${monk.currentHP}/${monk.maxHP}\t Kombo: ${monk.resurse}/${monk.maxResurse}")
    println()
    Thread.sleep(SLEEP_TIME / 2)
    println()

}

