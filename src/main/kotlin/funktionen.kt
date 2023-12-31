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

var boss = Boss("Der Schwarze Ritter", 5000, 10000)

//Der boss beschwört einen helfer

var bossHelper = BossHelper("Skelett-Krieger", 3000, 3000)

var enemys: MutableList<Opponent> = mutableListOf(boss)

var scroll1 = Scroll("Rolle der Wiederbelebung", 1)
var scroll2 = Scroll("Rolle des Doppelten Schadens", 3)
var potion = Healpotion("Heiltrank", 3)

var bag: MutableList<Item> = mutableListOf(potion, scroll2, scroll1)


//Boss wird in der konsole ausgegeben
fun createBoss() {

    println()
    Thread.sleep(SLEEP_TIME / 2)
    println("--- ${boss.name} ---")
    println("Name: ${boss.name}\t")
    println("HP: ${boss.hp}/${boss.maxHP}")
    println()
}

fun enemyInFight() {

    if (enemys.size == 2) {
        println()
        println("--- Der Boss ---\t\t\t\t --- Der Helfer ---")
        println("--- ${boss.name} ---\t\t --- ${bossHelper.name} ---")
        println("HP: ${boss.hp}/${boss.maxHP}\t\t\t\t\t HP: ${bossHelper.hp}/${bossHelper.maxHP}")
        println()

    } else {
        println()
        println("--- Der Boss ---")
        println("--- ${boss.name} ---")
        println("HP: ${boss.hp}/${boss.maxHP}")
        println()
    }
}

fun createBossHelper() {

    Thread.sleep(SLEEP_TIME / 2)
    println("--- Der '${bossHelper.name}' ist erschienen---")
    println("Name: ${bossHelper.name}\t")
    println("HP: ${bossHelper.hp}/${bossHelper.maxHP}")
    println()
}

fun createHeroes() {

    println("--- Klasse Krieger ---\t\t\t\t" + "--- Klasse Magier ---\t\t\t\t" + "--- Klasse Mönch ---")
    println("Name: ${warrior.name}\tLevel: ${warrior.startLevel}\t\t\t\t" + "Name: ${mage.name }\t Level: ${mage.startLevel}\t\t\t\t" + "Name: ${monk.name}\tLevel: ${monk.startLevel}")
    println("HP: ${warrior.currentHP}/${warrior.maxHP}\tWut: ${warrior.resurce}/${warrior.maxResource}\t\t\t\t" + "HP: ${mage.currentHP}/${mage.maxHP}\t Mana: ${mage.resurce}/${mage.maxResource}\t\t\t" + "HP: ${monk.currentHP}/${monk.maxHP}\t Chakra: ${monk.resurce}/${monk.maxResource}")
    println()

}

fun moreDmgBoss(hero: Hero){

    val dmgAttack1 = hero.attacke.values.elementAt(0)
    val dmgAttack2 = hero.attacke.values.elementAt(1)
    val dmgAttack3 = hero.attacke.values.elementAt(2)

    val newDmgAttack1 = dmgAttack1 * 2
    val newDmgAttack2 = dmgAttack2 * 2
    val newDmgAttack3 = dmgAttack3 * 2

    hero.attacke[hero.attacke.keys.elementAt(0)] = newDmgAttack1
    hero.attacke[hero.attacke.keys.elementAt(1)] = newDmgAttack2
    hero.attacke[hero.attacke.keys.elementAt(2)] = newDmgAttack3

    hero.attack(boss, hero.attacke, hero.resurce, hero.maxResource)

    hero.attacke[hero.attacke.keys.elementAt(0)] = dmgAttack1
    hero.attacke[hero.attacke.keys.elementAt(1)] = dmgAttack2
    hero.attacke[hero.attacke.keys.elementAt(2)] = dmgAttack3

    hero.hasBuff = false

}

fun moreDmgBossHelper(hero: Hero){

    val dmgAttack1 = hero.attacke.values.elementAt(0)
    val dmgAttack2 = hero.attacke.values.elementAt(1)
    val dmgAttack3 = hero.attacke.values.elementAt(2)

    val newDmgAttack1 = dmgAttack1 * 2
    val newDmgAttack2 = dmgAttack2 * 2
    val newDmgAttack3 = dmgAttack3 * 2

    hero.attacke[hero.attacke.keys.elementAt(0)] = newDmgAttack1
    hero.attacke[hero.attacke.keys.elementAt(1)] = newDmgAttack2
    hero.attacke[hero.attacke.keys.elementAt(2)] = newDmgAttack3

    hero.attack(bossHelper, hero.attacke, hero.resurce, hero.maxResource)

    hero.attacke[hero.attacke.keys.elementAt(0)] = dmgAttack1
    hero.attacke[hero.attacke.keys.elementAt(1)] = dmgAttack2
    hero.attacke[hero.attacke.keys.elementAt(2)] = dmgAttack3

    hero.hasBuff = false

}

fun heroHasDot(target: MutableList<Hero>, attack: Map<String, Int>){

    for (hero in target)
        if (hero.isDead && hero.hasDebuff){
            hero.hasDebuff = false
            boss.dotActiv = false
        } else if (hero.hasDebuff && hero.currentHP >= hero.maxHP * 0.2){
            val atkNamen = "Blutung"
            println("'${hero.name}' erleidet durch die offene Wunde ${attack[atkNamen]?.div(2)} schaden")
            hero.currentHP -= attack[atkNamen]!! / 2
            println("${hero.name} hat noch ${hero.currentHP}/${hero.maxHP}")
            println()
        } else if (hero.hasDebuff && hero.currentHP <= hero.maxHP * 0.2){
            println("Die Wunde von '${hero.name}' hat aufgehört zu bluten")
            println()
            hero.hasDebuff = false
            boss.dotActiv = false
        }
}

fun theFight() {

    if (enemys.size == 1) {

        for (hero in chars) {
            if (hero.hasDebuff){
                heroHasDot(chars, boss.attack)
//                continue
            }
            if (hero.isDead) {
                continue
            } else {
                enemyInFight()
                createHeroes()
                if (hero.hasBuff){
                    moreDmgBoss(hero)
                    continue
                }
                hero.attack(boss, hero.attacke, hero.resurce, hero.maxResource)
            }
        }
        if (boss.hp > 0) {
            boss.attack(chars, boss.attack)
        }
    } else {
        var bossHelperAlive = true

        for (hero in chars) {
            if (hero.hasDebuff){
                heroHasDot(chars, boss.attack)
//                continue
            }
            if (hero.isDead) {
                continue
            }
            if (enemys.isEmpty()) {
                break
            }
//            println()
            enemyInFight()
            createHeroes()
            if (bossHelperAlive) {
                println("${hero.classPlayer}: ${hero.name} - Wen wollen Sie angreifen?  ")
                for (i in enemys) {
                    println("${enemys.indexOf(i) + 1} - ${i.name} - HP: ${i.hp}/${i.maxHP}")
                }
                var input: Int? = null

//                Schleife zur richtigen Eingabe

                while (input !in (1..enemys.size)) {
                    try {
                        input = readlnOrNull()?.toInt()
                        if (input != null) {
                            if (input > enemys.size) {
                                println("Ungültige Eingabe. Bitte geben Sie 1 oder ${enemys.size} ein.")
                            }
                        }
                    } catch (e: NumberFormatException) {
//                        wenn ein String eingegeben wird
                        println("Ungültige Eingabe. Bitte geben Sie 1 oder ${enemys.size} ein.")
                    }
                }
                if (input == 1) {
                    if (hero.hasBuff){
                        moreDmgBoss(hero)
                        continue
                    } else {
                        hero.attack(boss, hero.attacke, hero.resurce, hero.maxResource)
                    }

                } else {
                    if (hero.hasBuff){
                        moreDmgBossHelper(hero)
                        continue
                    }else {
                        hero.attack(bossHelper, hero.attacke, hero.resurce, hero.maxResource)
                    }
                    if (bossHelper.hp <= 0) {
                        bossHelperAlive = false
                    }
                }
            } else {
                if (enemys.isEmpty()) {
                    break
                } else {
                    if (hero.hasBuff){
                        moreDmgBoss(hero)
                    }else {
                        hero.attack(boss, hero.attacke, hero.resurce, hero.maxResource)
                    }
                }
            }
        }
        if (enemys.isEmpty()) {

        } else {
            for (enemy in enemys) {
                if (deadChars.size == 3) {
                    break
                } else {
                    enemy.attack(chars, enemy.attack)
                }
            }
        }
    }
}




