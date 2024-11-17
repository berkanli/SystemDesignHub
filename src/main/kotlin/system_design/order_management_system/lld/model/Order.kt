package system_design.order_management_system.lld.model

import system_design.order_management_system.lld.model.enums.OrderStatus

data class Order(
    val id: String,
    val userId: String,
    val items: List<OrderItem>,
    val status: OrderStatus,
    val totalAmount: Double,
    val createdAt: Long,
    val updatedAt: Long
)