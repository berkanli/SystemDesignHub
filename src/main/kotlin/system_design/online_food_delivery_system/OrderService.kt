package system_design.online_food_delivery_system

import java.time.LocalDateTime

class OrderService {
    private val orders = mutableListOf<Order>()

    fun placeOrder(user: User, restaurant: Restaurant, menuItems: List<MenuItem>): Order {
        val totalPrice = menuItems.sumOf { it.price }

        val order = Order(
            id = generateId(),
            user = user,
            restaurant = restaurant,
            items = menuItems,
            totalPrice = totalPrice,
            status = OrderStatus.PLACED,
            createdAt = LocalDateTime.now()
        )

        orders.add(order)
        return order
    }

    fun updateOrderStatus(orderId: String, status: OrderStatus): Boolean {
        val order = orders.find { it.id == orderId } ?: return false
        orders[orders.indexOf(order)] = order.copy(status = status)
        return true
    }

    fun getOrdersByUser(userId: Int): List<Order> {
        return orders.filter { it.user.id == userId }
    }

    private fun generateId(): String {
        return "Order-${System.currentTimeMillis()}"
    }
}