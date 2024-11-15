package system_design.flight_booking_system.models

import system_design.flight_booking_system.models.enums.PaymentStatus

data class Payment(
    val paymentId: String,
    val amount: Double,
    val reservation: Reservation,
    var status: PaymentStatus = PaymentStatus.PENDING
) {
    fun completePayment() {
        status = PaymentStatus.COMPLETED
        reservation.confirmReservation()
    }

    fun failPayment() {
        status = PaymentStatus.FAILED
        reservation.cancelReservation()
    }
}