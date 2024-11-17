package system_design.order_management_system.lld.service

import system_design.order_management_system.lld.InventoryRepository
import system_design.order_management_system.lld.model.Order

class InventoryService(private val inventoryRepo: InventoryRepository) {

    fun isAvailable(itemId: String, quantity: Int): Boolean {
        val inventory = inventoryRepo.getInventory(itemId)
        return inventory.availableStock >= quantity
    }

    fun reserveStock(order: Order) {
        order.items.forEach { item ->
            val inventory = inventoryRepo.getInventory(item.itemId)
            if (inventory.availableStock < item.quantity) {
                throw IllegalStateException("Insufficient stock for item ${item.itemId}")
            }
            inventoryRepo.updateStock(item.itemId, inventory.availableStock - item.quantity)
        }
    }

    fun releaseStock(order: Order) {
        order.items.forEach { item ->
            val inventory = inventoryRepo.getInventory(item.itemId)
            inventoryRepo.updateStock(item.itemId, inventory.availableStock + item.quantity)
        }
    }
}