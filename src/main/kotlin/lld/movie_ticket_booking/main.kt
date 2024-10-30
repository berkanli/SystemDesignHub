package lld.movie_ticket_booking

import kotlinx.coroutines.runBlocking
import lld.movie_ticket_booking.controller.BookingController
import lld.movie_ticket_booking.enums.SeatStatus
import lld.movie_ticket_booking.enums.SeatType
import lld.movie_ticket_booking.model.Screen
import lld.movie_ticket_booking.model.Seat
import lld.movie_ticket_booking.model.Theater
import lld.movie_ticket_booking.utils.ServiceContext
import java.time.LocalDateTime

fun main() = runBlocking {
    val context = ServiceContext()
    val bookingController = BookingController(
        context.userService,
        context.showtimeService,
        context.seatService,
        context.bookingService,
        context.paymentService
    )

    // Register a user and set up movie, theater, seats, and showtime for testing
    val user = context.userService.registerUser("Alice", "alice@example.com", "555-1234")
    val movie = context.movieService.addMovie("Inception", "Sci-Fi", 148)
    val theater = Theater("theater_01", "Downtown Theater", "123 Main St", mutableListOf())

    // Create seats with AVAILABLE status
    val seats = (1..10).map { Seat("seat_$it", "A$it", SeatType.REGULAR, SeatStatus.AVAILABLE) }
    val screen = Screen("screen_01", seats.size, seats, theater)
    theater.screens.add(screen)

    // Add a showtime, which should now include the available seats from the screen
    val showtime = context.showtimeService.addShowtime(movie, screen, LocalDateTime.now().plusDays(1))

    // Execute a booking and print result
    val bookingResult = bookingController.bookTickets(user.userID, showtime.showtimeID, listOf("seat_1", "seat_2"))
    println(bookingResult.getOrElse { "Booking failed: ${it.message}" })
}
