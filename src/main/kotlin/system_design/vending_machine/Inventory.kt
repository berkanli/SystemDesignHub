package system_design.vending_machine

class Inventory {
    private val stock = mutableMapOf<Item, Int>()

    fun addItem(item: Item, quantity: Int){
        stock[item] = stock.getOrDefault(item, 0) + quantity
    }

    fun getItemQuantity(item: Item): Int {
        return stock[item] ?: 0
    }

    fun dispenseItem(item: Item): Boolean {
        val quantity = stock[item] ?: return false
        if (quantity > 0) {
            stock[item] = quantity - 1
            return true
        }
        return false
    }
}