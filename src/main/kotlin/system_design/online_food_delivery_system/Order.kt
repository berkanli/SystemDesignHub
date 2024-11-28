package system_design.online_food_delivery_system

import java.time.LocalDateTime

data class Order(
    val id: String,
    val user: User,
    val restaurant: Restaurant,
    val items: List<MenuItem>,
    val totalPrice: Double,
    val status: OrderStatus,
    val createdAt: LocalDateTime
)
