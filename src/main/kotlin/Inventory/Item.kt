package Inventory


import chars

open class Item (var name: String, var anzahl: Int) {

open fun healPotion() {
    var needPoition = chars.filter { it.currentHP < it.maxHP }

    for (i in needPoition){
        println("${needPoition.indexOf(i) + 1} - ${i.name} - HP: ${i.currentHP}/${i.maxHP}")
    }
    var input: Int? = null

    while (input !in (1..needPoition.size)) {
        try {
            input = readlnOrNull()?.toInt()
            if (input != null) {
                if (input > needPoition.size) {
                    println("Ungültige Eingabe. Bitte geben Sie 1 oder ${needPoition.size} ein.")
                }
            }
        } catch (e: NumberFormatException) {
//                        wenn ein String eingegeben wird
            println("Ungültige Eingabe. Bitte geben Sie 1 oder ${needPoition.size} ein.")
        }
    }
}


}