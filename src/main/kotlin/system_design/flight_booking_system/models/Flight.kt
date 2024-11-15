package system_design.flight_booking_system.models

import java.time.LocalDateTime

data class Flight(
    val flightNumber: String,
    val origin: String,
    val destination: String,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val airplane: Airplane,
    val seats: MutableList<Seat> = mutableListOf()
) {
    fun getAvailableSeats(): List<Seat> = seats.filter { it.isAvailable }
}
