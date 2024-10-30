package lld.movie_ticket_booking.model

data class Screen(
    val screenID: String,
    val totalSeats: Int,
    val seats: List<Seat>,
    val theater: Theater
)