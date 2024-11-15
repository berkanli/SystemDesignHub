package system_design.flight_booking_system.services

import system_design.flight_booking_system.models.Reservation

class NotificationService {
    fun sendBookingConfirmation(reservation: Reservation) {
        println("Notification: Your booking for flight ${reservation.flight.flightNumber} has been confirmed.")
    }

    fun sendCancellationConfirmation(reservation: Reservation) {
        println("Notification: Your booking for flight ${reservation.flight.flightNumber} has been canceled.")
    }
}