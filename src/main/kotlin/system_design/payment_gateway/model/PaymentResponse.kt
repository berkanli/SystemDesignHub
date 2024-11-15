package system_design.payment_gateway.model

import system_design.payment_gateway.PaymentStatus

data class PaymentResponse(
    val transactionId: String,
    val status: PaymentStatus,
    val message: String? = null
)