package system_design.inventory_management_system

class NotificationService {
    fun notifyLowStock(product: Product) {
        if (product.quantityInStock < 10) {
            println("Low stock alert for product: ${product.name}")
        }
    }

    fun notifyOrderStatus(order: Order) {
        println("Order ${order.id} is now ${order.status}")
    }
}