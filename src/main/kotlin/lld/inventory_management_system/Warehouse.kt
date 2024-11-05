package lld.inventory_management_system

data class Warehouse(
    val id: String,
    val name: String,
    val location: String,
    val inventory: MutableMap<String, Int> = mutableMapOf()
) {
    fun addProduct(productId: String, quantity: Int) {
        inventory[productId] = inventory.getOrDefault(productId, 0) + quantity
    }

    fun removeProduct(productId: String, quantity: Int): Boolean {
        val currentStock = inventory.getOrDefault(productId, 0)
        if (currentStock >= quantity) {
            inventory[productId] = currentStock - quantity
            return true
        }
        return false
    }
}
