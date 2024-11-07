package lld.payment_gateway

interface PaymentProcessor {
    fun process(transaction: Transaction): PaymentStatus
}

class MockPaymentProcessor : PaymentProcessor {
    override fun process(transaction: Transaction): PaymentStatus {
        // Simulate payment processing logic
        return PaymentStatus.SUCCESS // For simplicity, returning success
    }
}