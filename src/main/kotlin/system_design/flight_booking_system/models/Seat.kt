package system_design.flight_booking_system.models

import system_design.flight_booking_system.models.enums.SeatClass

data class Seat(
    val seatNumber: String,
    val classType: SeatClass,
    var isAvailable: Boolean = true
)
