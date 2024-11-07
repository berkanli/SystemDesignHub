package lld.payment_gateway

data class PaymentRequest(
    val amount: Double,
    val currency: String,
    val sourceAccountId: String,
    val destinationAccountId: String,
    val paymentMethod: PaymentMethod,
    val description: String
)