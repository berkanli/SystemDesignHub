package lld.movie_ticket_booking.model

data class Movie(
    val movieID: String,
    val title: String,
    val genre: String,
    val duration: Int, // in minutes
    val showtimes: MutableList<Showtime> = mutableListOf()
)