package lld.payment_gateway

import lld.payment_gateway.model.Transaction

interface PaymentProcessor {
    fun process(transaction: Transaction): PaymentStatus
}

class MockPaymentProcessor : PaymentProcessor {
    override fun process(transaction: Transaction): PaymentStatus {
        // Simulate payment processing logic
        return PaymentStatus.SUCCESS // For simplicity, returning success
    }
}