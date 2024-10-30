package lld.movie_ticket_booking.controller

import lld.movie_ticket_booking.enums.PaymentStatus
import lld.movie_ticket_booking.services.*

class BookingController(
    private val userService: UserService,
    private val showtimeService: ShowtimeService,
    private val seatService: SeatService,
    private val bookingService: BookingService,
    private val paymentService: PaymentService
) {
    suspend fun bookTickets(userId: String, showtimeId: String, seatIds: List<String>): Result<String> {
        val user = userService.getUser(userId) ?: return Result.failure(Exception("User not found"))
        val showtime = showtimeService.getShowtime(showtimeId) ?: return Result.failure(Exception("Showtime not found"))

        // Step 1: Check and reserve selected seats
        val seats = seatService.checkAndReserveSeats(showtime, seatIds)
            ?: return Result.failure(Exception("Some selected seats are not available"))

        // Step 2: Create the booking in pending state
        val booking = bookingService.createPendingBooking(user, showtime, seats)
        val amount = seats.size * 10.0

        // Step 3: Process payment
        val payment = paymentService.processPayment(amount)
        return if (payment.status == PaymentStatus.PAID) {
            // Step 4: Confirm booking upon successful payment
            bookingService.confirmBooking(booking, payment)
            Result.success("Booking confirmed and payment completed")
        } else {
            seatService.releaseSeats(seats) // Release seats if payment fails
            Result.failure(Exception("Payment failed, seats released"))
        }
    }
}