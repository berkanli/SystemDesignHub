package lld.vending_machine

class Transaction(
    private val inventory: Inventory,
    private val payment: Payment,
    private val display: Display
) {
    fun purchaseItem(item: Item) {
        if (inventory.getItemQuantity(item) == 0) {
            display.showMessage("Item out of stock")
            return
        }
        display.showPrice(item.price)

        if (payment.processPayment(item.price)) {
            if (inventory.dispenseItem(item)) {
                display.showMessage("Dispensing ${item.name}")
            } else {
                display.showMessage("Failed to dispense item")
            }
        } else {
            display.showMessage("Payment failed")
        }
    }
}