package system_design.car_rental_system.controllers

import system_design.car_rental_system.models.Payment
import system_design.car_rental_system.services.PaymentService

class PaymentController(private val paymentService: PaymentService) {

    fun makePayment(amount: Double, reservationId: String): Payment {
        return paymentService.makePayment(amount, reservationId)
    }

    fun getPaymentsForReservation(reservationId: String): List<Payment> {
        return paymentService.getPaymentsForReservation(reservationId)
    }
}