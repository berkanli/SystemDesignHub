package system_design.order_management_system.lld

import system_design.order_management_system.lld.model.OrderItem
import system_design.order_management_system.lld.service.InventoryService
import system_design.order_management_system.lld.service.OrderService
import system_design.order_management_system.lld.service.PaymentService

fun main() {
    // Initialize dependencies
    val inventoryRepo = InMemoryInventoryRepository()
    val inventoryService = InventoryService(inventoryRepo)
    val paymentGateway = StripePaymentGateway()
    val paymentService = PaymentService(paymentGateway)
    val orderService = OrderService(inventoryService, paymentService)

    // Create an order
    val items = listOf(
        OrderItem(itemId = "item1", quantity = 2, price = 10.0),
        OrderItem(itemId = "item2", quantity = 1, price = 20.0)
    )

    val order = orderService.createOrder(userId = "user123", items = items)
    println("Order created: $order")

    // Cancel the order
    val cancelSuccess = orderService.cancelOrder(order.id)
    println("Order cancellation successful: $cancelSuccess")
}