package system_design.order_management_system.lld.service

import system_design.order_management_system.lld.model.Order
import system_design.order_management_system.lld.model.OrderItem
import system_design.order_management_system.lld.model.enums.OrderStatus

class OrderService(private val inventoryService: InventoryService, private val paymentService: PaymentService) {
    fun createOrder(userId: String, items: List<OrderItem>): Order {
        val orderId = generateOrderId()
        val totalAmount = items.sumOf { it.quantity * it.price }

        items.forEach { item ->
            if (!inventoryService.isAvailable(item.itemId, item.quantity)) {
                throw IllegalArgumentException("Item ${item.itemId} is out of stock")
            }
        }

        val order = Order(
            id = orderId,
            userId = userId,
            items = items,
            status = OrderStatus.PENDING,
            totalAmount = totalAmount,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        inventoryService.reserveStock(order)
        paymentService.processPayment(order)

        return order
    }

    fun cancelOrder(orderId: String): Boolean {
        val order = getOrderById(orderId)

        if (order.status != OrderStatus.PENDING) {
            throw IllegalStateException("Only pending orders can be cancelled")
        }

        order.status = OrderStatus.CANCELLED
        inventoryService.releaseStock(order)
        paymentService.refundPayment(order)

        return true
    }
}