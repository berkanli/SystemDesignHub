package system_design.payment_gateway.model

import system_design.payment_gateway.PaymentMethod

data class PaymentRequest(
    val amount: Double,
    val currency: String,
    val sourceAccountId: String,
    val destinationAccountId: String,
    val paymentMethod: PaymentMethod,
    val description: String
)