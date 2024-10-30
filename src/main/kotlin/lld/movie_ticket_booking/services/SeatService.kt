package lld.movie_ticket_booking.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lld.movie_ticket_booking.enums.SeatStatus
import lld.movie_ticket_booking.model.Seat
import lld.movie_ticket_booking.model.Showtime

class SeatService {
    suspend fun checkAndReserveSeats(showtime: Showtime, seatIds: List<String>): List<Seat>? =
        withContext(Dispatchers.IO) {
            val selectedSeats = showtime.availableSeats.filter { seatIds.contains(it.seatID) }

            println("Selected seats: $selectedSeats") // Debugging line
            println("Seat statuses: ${selectedSeats.map { it.status }}") // Debugging line

            return@withContext if (selectedSeats.size == seatIds.size && selectedSeats.all { it.status == SeatStatus.AVAILABLE }) {
                selectedSeats.forEach { it.status = SeatStatus.RESERVED }
                selectedSeats
            } else null
        }


    suspend fun releaseSeats(seats: List<Seat>) = withContext(Dispatchers.IO) {
        seats.forEach { it.status = SeatStatus.AVAILABLE }
    }
}