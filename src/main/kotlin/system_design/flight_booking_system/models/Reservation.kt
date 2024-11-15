package system_design.flight_booking_system.models

import system_design.flight_booking_system.models.enums.ReservationStatus


data class Reservation(
    val reservationId: String,
    val flight: Flight,
    val passenger: Passenger,
    val seats: MutableList<Seat> = mutableListOf(),
    var status: ReservationStatus = ReservationStatus.PENDING
) {
    fun confirmReservation() {
        status = ReservationStatus.CONFIRMED
        seats.forEach { it.isAvailable = false }
    }

    fun cancelReservation() {
        status = ReservationStatus.CANCELLED
        seats.forEach { it.isAvailable = true }
    }
}
