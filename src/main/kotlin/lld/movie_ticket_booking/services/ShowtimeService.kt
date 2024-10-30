package lld.movie_ticket_booking.services

import lld.movie_ticket_booking.enums.SeatStatus
import lld.movie_ticket_booking.model.Movie
import lld.movie_ticket_booking.model.Screen
import lld.movie_ticket_booking.model.Seat
import lld.movie_ticket_booking.model.Showtime
import java.time.LocalDateTime

class ShowtimeService {
    private val showtimes = mutableMapOf<String, Showtime>()

    fun addShowtime(movie: Movie, screen: Screen, startTime: LocalDateTime): Showtime {
        val showtimeID = generateShowtimeID()

        // Populate available seats from the screen
        val showtime = Showtime(
            showtimeID = showtimeID,
            startTime = startTime,
            screen = screen,
            movie = movie,
            availableSeats = screen.seats.toMutableList() // Assign screen seats as available seats
        )

        showtimes[showtimeID] = showtime
        return showtime
    }

    fun getShowtime(showtimeID: String): Showtime? {
        return showtimes[showtimeID]
    }

    private fun generateShowtimeID(): String {
        return "showtime_${System.currentTimeMillis()}"
    }
}