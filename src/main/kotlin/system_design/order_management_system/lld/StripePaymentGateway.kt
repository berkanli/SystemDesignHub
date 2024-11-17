package system_design.order_management_system.lld

import system_design.order_management_system.lld.model.Payment
import system_design.order_management_system.lld.model.enums.PaymentStatus

class StripePaymentGateway : IPaymentGateway {
    override fun charge(userId: String, amount: Double): Payment {
        println("Charging user $userId an amount of $amount using Stripe...")
        // Simulate successful payment
        return Payment(
            id = generatePaymentId(),
            orderId = "mock-order-id",
            userId = userId,
            status = PaymentStatus.COMPLETED,
            amount = amount,
            paymentMethod = "Stripe",
            createdAt = System.currentTimeMillis()
        )
    }

    override fun refund(orderId: String, amount: Double): Boolean {
        println("Refunding $amount for order $orderId via Stripe...")
        // Simulate successful refund
        return true
    }

    private fun generatePaymentId(): String {
        return "payment-" + System.currentTimeMillis()
    }
}