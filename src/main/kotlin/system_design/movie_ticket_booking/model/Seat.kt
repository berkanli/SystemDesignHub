package system_design.movie_ticket_booking.model

import system_design.movie_ticket_booking.enums.SeatStatus
import system_design.movie_ticket_booking.enums.SeatType

data class Seat(
    val seatID: String,
    val seatNumber: String,
    val type: SeatType,
    var status: SeatStatus = SeatStatus.AVAILABLE
)