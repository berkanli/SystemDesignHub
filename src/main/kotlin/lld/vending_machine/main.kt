package lld.vending_machine

fun main() {
    val vendingMachine = VendingMachine()

    val chips = Item(1, "Chips", 1.50)
    val soda = Item(2, "Soda", 1.25)

    vendingMachine.addItem(chips, 10)
    vendingMachine.addItem(soda, 5)

    vendingMachine.selectItem(chips)  // Simulates purchasing chips
    vendingMachine.selectItem(soda)   // Simulates purchasing soda
}