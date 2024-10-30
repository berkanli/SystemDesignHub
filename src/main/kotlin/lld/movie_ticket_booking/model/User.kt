package lld.movie_ticket_booking.model

data class User(
    val userID: String,
    val name: String,
    val email: String,
    val phoneNumber: String? = null,
    val bookings: MutableList<Booking> = mutableListOf()
)
