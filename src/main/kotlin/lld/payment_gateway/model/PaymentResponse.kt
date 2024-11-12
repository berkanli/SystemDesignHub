package lld.payment_gateway.model

import lld.payment_gateway.PaymentStatus

data class PaymentResponse(
    val transactionId: String,
    val status: PaymentStatus,
    val message: String? = null
)