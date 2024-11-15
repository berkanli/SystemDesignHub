package system_design.movie_ticket_booking.model

import system_design.movie_ticket_booking.enums.BookingStatus

data class Booking(
    val bookingID: String,
    val user: User,
    val showtime: Showtime,
    val seats: List<Seat>,
    var status: BookingStatus = BookingStatus.PENDING,
    var payment: Payment
)
