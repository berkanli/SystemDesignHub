package system_design.flight_booking_system.services

import system_design.flight_booking_system.models.Flight
import system_design.flight_booking_system.models.Passenger
import system_design.flight_booking_system.models.Reservation
import system_design.flight_booking_system.models.enums.ReservationStatus
import java.time.LocalDateTime

sealed class ReservationError {
    object SeatsUnavailable: ReservationError()
    object FlightNotFound: ReservationError()
}

class FlightService(private val flights: MutableList<Flight>) {
    private val lock = Any()

    fun searchFlights(origin: String, destination: String, date: LocalDateTime): List<Flight> {
        return flights.filter { flight ->
            flight.origin == origin && flight.destination == destination &&
                    flight.departureTime.toLocalDate() == date.toLocalDate()
        }
    }

    fun createReservation(flight: Flight, passenger: Passenger, seatNumbers: List<String>): Result<Reservation> {
        synchronized(lock) {
            val selectedSeats = flight.seats.filter { seat ->
                seat.seatNumber in seatNumbers && seat.isAvailable
            }

            if (selectedSeats.size != seatNumbers.size) {
                return Result.failure(Exception("Some seats are already reserved."))
            }

            selectedSeats.forEach { it.isAvailable = false }

            val reservation = Reservation(
                reservationId = generateReservationId(),
                flight = flight,
                passenger = passenger,
                seats = selectedSeats.toMutableList(),
                status = ReservationStatus.PENDING
            )

            println("Reservation created successfully for passenger ${passenger.name}.")
            return Result.success(reservation)
        }
    }

    fun cancelReservation(reservation: Reservation): Result<Unit> {
        reservation.cancelReservation()
        println("Reservation ${reservation.reservationId} has been canceled.")
        return Result.success(Unit)
    }

    private fun generateReservationId(): String {
        return "RES-${System.currentTimeMillis()}"
    }
}