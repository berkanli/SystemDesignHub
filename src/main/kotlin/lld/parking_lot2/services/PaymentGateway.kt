package lld.parking_lot2.services

import lld.parking_lot2.models.Ticket
import java.time.LocalDateTime

// In Kotlin, we use the object keyword to create a singleton, a design pattern that restricts a class to a single
// instance. Here are the reasons why PaymentGateway is built as an object
object PaymentGateway {
    fun processPayment(ticket: Ticket): Double {
        val hoursParked = (LocalDateTime.now().hour - ticket.entryTime.hour).toDouble().coerceAtLeast(1.0)
        val ratePerHour = 10.0
        return hoursParked * ratePerHour
    }
}