package lld.flight_booking_system.models

import lld.flight_booking_system.models.enums.SeatClass

data class Seat(
    val seatNumber: String,
    val classType: SeatClass,
    var isAvailable: Boolean = true
)
