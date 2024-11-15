package system_design.movie_ticket_booking.services

import system_design.movie_ticket_booking.model.Booking
import system_design.movie_ticket_booking.model.User

class UserService {
    private val users = mutableMapOf<String, User>()

    fun registerUser(name: String, email: String, phoneNumber: String?): User {
        val userID = generateUserID()
        val user = User(userID, name, email, phoneNumber)
        users[userID] = user
        return user
    }

    fun getUser(userID: String): User? {
        return users[userID]
    }

    fun getUserBookings(userID: String): List<Booking>? {
        return users[userID]?.bookings
    }

    private fun generateUserID(): String {
        return "user_${System.currentTimeMillis()}"
    }
}