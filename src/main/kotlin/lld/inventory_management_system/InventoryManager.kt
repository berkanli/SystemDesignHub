package lld.inventory_management_system

class InventoryManager(
    private val warehouses: MutableList<Warehouse> = mutableListOf(),
    private val products: MutableMap<String, Product> = mutableMapOf()
) {
    private val orders: MutableList<Order> = mutableListOf()

    fun addProduct(product: Product) {
        products[product.id] = product
    }

    fun addWarehouse(warehouse: Warehouse) {
        warehouses.add(warehouse)
    }

    fun placeOrder(order: Order): Boolean {
        val product = products[order.productId] ?: return false
        if (product.quantityInStock >= order.quantity) {
            product.updateStock(-order.quantity)
            order.status = OrderStatus.SHIPPED
            orders.add(order)
            return true
        }
        return false
    }

    fun restockProduct(productId: String, quantity: Int, warehouseId: String): Boolean {
        val product = products[productId] ?: return false
        val warehouse = warehouses.find { it.id == warehouseId } ?: return false
        product.updateStock(quantity)
        warehouse.addProduct(productId, quantity)
        return true
    }

    fun getProductStock(productId: String): Int {
        return products[productId]?.quantityInStock ?: 0
    }
}
