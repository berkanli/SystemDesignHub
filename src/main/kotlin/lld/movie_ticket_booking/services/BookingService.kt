package lld.movie_ticket_booking.services

import lld.movie_ticket_booking.enums.BookingStatus
import lld.movie_ticket_booking.enums.PaymentStatus
import lld.movie_ticket_booking.model.*

class BookingService(private val seatService: SeatService) {
    fun createPendingBooking(user: User, showtime: Showtime, seats: List<Seat>): Booking {
        val booking = Booking(
            bookingID = "booking_${System.currentTimeMillis()}",
            user = user,
            showtime = showtime,
            seats = seats,
            status = BookingStatus.PENDING,
            payment = Payment("", PaymentStatus.PENDING, seats.size * 10.0)
        )
        user.bookings.add(booking)
        return booking
    }

    fun confirmBooking(booking: Booking, payment: Payment) {
        booking.status = BookingStatus.CONFIRMED
        booking.payment = payment
    }
}