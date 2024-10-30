package lld.movie_ticket_booking.utils

import lld.movie_ticket_booking.services.*

class ServiceContext {
    val userService = UserService()
    val movieService = MovieService()
    val seatService = SeatService()
    val showtimeService = ShowtimeService()
    val bookingService = BookingService(seatService)
    val paymentService = PaymentService()
}