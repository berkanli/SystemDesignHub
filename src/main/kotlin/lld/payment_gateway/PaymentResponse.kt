package lld.payment_gateway

data class PaymentResponse(
    val transactionId: String,
    val status: PaymentStatus,
    val message: String? = null
)