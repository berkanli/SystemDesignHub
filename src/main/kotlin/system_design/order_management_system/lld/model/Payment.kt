package system_design.order_management_system.lld.model

import system_design.order_management_system.lld.model.enums.PaymentStatus

data class Payment(
    val id: String,
    val orderId: String,
    val userId: String,
    val status: PaymentStatus,
    val amount: Double,
    val paymentMethod: String,
    val createdAt: Long
)