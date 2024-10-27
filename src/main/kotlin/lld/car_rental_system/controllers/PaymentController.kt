package lld.car_rental_system.controllers

import lld.car_rental_system.models.Payment
import lld.car_rental_system.services.PaymentService

class PaymentController(private val paymentService: PaymentService) {

    fun makePayment(amount: Double, reservationId: String): Payment {
        return paymentService.makePayment(amount, reservationId)
    }

    fun getPaymentsForReservation(reservationId: String): List<Payment> {
        return paymentService.getPaymentsForReservation(reservationId)
    }
}