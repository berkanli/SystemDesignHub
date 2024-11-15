package system_design.flight_booking_system.models

import system_design.flight_booking_system.models.enums.ReservationStatus

data class Reservation(
    val reservationId: String,
    val flight: Flight,
    val passenger: Passenger,
    val seats: MutableList<Seat> = mutableListOf(),
    var status: ReservationStatus = ReservationStatus.PENDING
)