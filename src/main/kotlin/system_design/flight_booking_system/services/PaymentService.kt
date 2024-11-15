package system_design.flight_booking_system.services

import system_design.flight_booking_system.models.Payment
import system_design.flight_booking_system.models.Reservation
import system_design.flight_booking_system.models.enums.PaymentStatus
import system_design.flight_booking_system.models.enums.ReservationStatus

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
            payment.status = PaymentStatus.COMPLETED
            reservation.status = ReservationStatus.CONFIRMED
            reservation.seats.forEach { it.isAvailable = false }
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