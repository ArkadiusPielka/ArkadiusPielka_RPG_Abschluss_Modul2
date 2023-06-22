fun main() {

    println("Herzlich willkommen")
    println()
    Thread.sleep(SLEEP_TIME)
    println("Die helden werden generiert")
    println()
    Thread.sleep(SLEEP_TIME)
    createHeroes()
    println("Der Boss wird erstellt:")
    createBoss()
    Thread.sleep(SLEEP_TIME)
    boss.spezialAttackBoss()
    println("Der Kampf beginnt!!")
    println()
    var i = 0
    do {
        if (deadChars.size == 3) {
            println("Game OVER ... alle Helden sind Valhalla an Odins seite.")
            break
        } else if (enemys.isEmpty()) {
            println()
            println("Herzlichen glückwunsch Sie haben Gewonnen.")
            break
        }
        println("-------------------- Runde ${i + 1} --------------------")

        theFight()

        i++
    } while (boss.hp > 0 || (warrior.currentHP > 0 || monk.currentHP > 0 || mage.currentHP > 0))
}


