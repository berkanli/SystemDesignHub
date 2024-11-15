package system_design.movie_ticket_booking.model

import java.time.LocalDateTime

data class Showtime(
    val showtimeID: String,
    val startTime: LocalDateTime,
    val screen: Screen,
    val movie: Movie,
    val availableSeats: MutableList<Seat> = mutableListOf()
)