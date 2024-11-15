package system_design.inventory_management_system

class InventoryApp {
    private val inventoryManager = InventoryManager()
    private val notificationService = NotificationService()

    fun run() {
        // Sample Data
        val product1 = Product("1", "Laptop", "SKU123", 1000.0, 50)
        val warehouse1 = Warehouse("1", "Main Warehouse", "123 Warehouse St")

        inventoryManager.addProduct(product1)
        inventoryManager.addWarehouse(warehouse1)

        // Place an Order
        val order1 = Order("101", product1.id, 5)
        if (inventoryManager.placeOrder(order1)) {
            notificationService.notifyOrderStatus(order1)
        }

        // Check Stock
        if (inventoryManager.getProductStock(product1.id) < 10) {
            notificationService.notifyLowStock(product1)
        }
    }
}

fun main() {
    val inventoryApp = InventoryApp()
    inventoryApp.run()
}