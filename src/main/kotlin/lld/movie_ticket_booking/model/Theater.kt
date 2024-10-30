package lld.movie_ticket_booking.model

data class Theater(
    val theaterID: String,
    val name: String,
    val location: String,
    val screens: MutableList<Screen> = mutableListOf()
)