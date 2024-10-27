package lld.car_rental_system.services

import lld.car_rental_system.models.Payment
import lld.car_rental_system.repositories.PaymentRepository
import lld.car_rental_system.utils.DateUtils
import java.time.LocalDate

class PaymentService(private val paymentRepository: PaymentRepository) {

    fun makePayment(amount: Double, reservationId: String): Payment {
        val payment = Payment(
            paymentId = generatePaymentId(),
            amount = amount,
            date = DateUtils.formatDate(LocalDate.now()),
            reservationId = reservationId,
            isPaid = true
        )

        paymentRepository.addPayment(payment)
        return payment
    }

    private fun generatePaymentId(): String {
        return "PAYMENT - ${paymentRepository.size + 1}"
    }

    fun getPaymentsForReservation(reservationId: String): List<Payment> {
        return paymentRepository.getPaymentsByReservationId(reservationId)
    }
}