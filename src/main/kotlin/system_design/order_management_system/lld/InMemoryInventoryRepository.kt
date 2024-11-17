package system_design.order_management_system.lld

import system_design.order_management_system.lld.model.Inventory

class InMemoryInventoryRepository : InventoryRepository {
    private val inventoryData: MutableMap<String, Inventory> = mutableMapOf()

    init {
        // Initialize with some dummy data
        inventoryData["item1"] = Inventory(itemId = "item1", availableStock = 100)
        inventoryData["item2"] = Inventory(itemId = "item2", availableStock = 50)
    }

    override fun getInventory(itemId: String): Inventory {
        return inventoryData[itemId]
            ?: throw IllegalArgumentException("Item with ID $itemId not found in inventory.")
    }

    override fun updateStock(itemId: String, newStock: Int) {
        if (!inventoryData.containsKey(itemId)) {
            throw IllegalArgumentException("Item with ID $itemId not found.")
        }
        inventoryData[itemId] = inventoryData[itemId]!!.copy(availableStock = newStock)
        println("Updated stock for item $itemId to $newStock")
    }
}