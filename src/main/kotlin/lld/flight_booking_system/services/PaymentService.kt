package lld.flight_booking_system.services

import lld.flight_booking_system.models.Payment
import lld.flight_booking_system.models.Reservation

class PaymentService {
    fun processPayment(reservation: Reservation, amount: Double): Result<Payment> {
        val payment = Payment(
            paymentId = generatePaymentId(),
            amount = amount,
            reservation = reservation
        )

        // Simulate payment success
        val paymentSuccess = true // In real-world, replace with actual payment logic
        return if (paymentSuccess) {
            payment.completePayment()
            println("Payment of $$amount completed for reservation ${reservation.reservationId}.")
            Result.success(payment)
        } else {
            Result.failure(Exception("Payment failed. Please try again."))
        }
    }

    private fun generatePaymentId(): String {
        return "PAY-${System.currentTimeMillis()}"
    }
}

sealed class PaymentError {
    object  PaymentFailed: PaymentError()
}