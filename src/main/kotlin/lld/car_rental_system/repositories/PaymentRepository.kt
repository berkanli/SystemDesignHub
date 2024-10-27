package lld.car_rental_system.repositories

import lld.car_rental_system.models.Payment

class PaymentRepository {
    private val payments = mutableListOf<Payment>()

    fun addPayment(payment: Payment) {
        payments.add(payment)
    }

    fun getPaymentsByReservationId(reservationId: String): List<Payment> {
        return payments.filter { it.reservationId == reservationId }
    }

    val size: Int
        get() = payments.size
}