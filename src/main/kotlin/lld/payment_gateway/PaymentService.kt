package lld.payment_gateway

interface PaymentService {
    fun initiatePayment(request: PaymentRequest): PaymentResponse
    fun validatePayment(request: PaymentRequest): Boolean
    fun processPayment(transactionId: String): PaymentStatus
    fun getTransactionStatus(transactionId: String): PaymentStatus
}