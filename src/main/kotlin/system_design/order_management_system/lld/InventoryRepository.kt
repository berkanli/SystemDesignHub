package system_design.order_management_system.lld

import system_design.order_management_system.lld.model.Inventory

interface InventoryRepository {
    fun getInventory(itemId: String): Inventory
    fun updateStock(itemId: String, newStock: Int)
}