package system_design.vending_machine

class VendingMachine {
    private val inventory = Inventory()
    private val payment = Payment()
    private val display = Display()
    private val transaction = Transaction(inventory, payment, display)

    fun addItem(item: Item, quantity: Int) {
        inventory.addItem(item, quantity)
    }

    fun selectItem(item: Item) {
        transaction.purchaseItem(item)
    }
}
