package lld.movie_ticket_booking.services

import lld.movie_ticket_booking.enums.PaymentStatus
import lld.movie_ticket_booking.model.Payment

class PaymentService {
    fun processPayment(amount: Double): Payment {
        val paymentID = generatePaymentID()
        val payment = Payment(paymentID, PaymentStatus.PENDING, amount)

        // Simulate payment processing
        val success = externalPaymentProvider(amount)
        payment.status = if (success) PaymentStatus.PAID else PaymentStatus.FAILED

        return payment
    }

    private fun externalPaymentProvider(amount: Double): Boolean {
        // Simulate integration with external payment API
        // Here, you would typically call an external API and handle responses
        return true // Assuming the payment is successful for simplicity
    }

    private fun generatePaymentID(): String {
        return "payment_${System.currentTimeMillis()}"
    }
}