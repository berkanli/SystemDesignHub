package lld.flight_booking_system

import lld.flight_booking_system.models.Airplane
import lld.flight_booking_system.models.Flight
import lld.flight_booking_system.models.Passenger
import lld.flight_booking_system.models.Seat
import lld.flight_booking_system.models.enums.SeatClass
import lld.flight_booking_system.services.FlightService
import lld.flight_booking_system.services.NotificationService
import lld.flight_booking_system.services.PaymentService
import java.time.LocalDateTime

fun main() {
    val airplane = Airplane("Boeing 737", 180)
    airplane.seatMap["1A"] = Seat("1A", SeatClass.ECONOMY)
    airplane.seatMap["1B"] = Seat("1B", SeatClass.ECONOMY)
    airplane.seatMap["1C"] = Seat("1C", SeatClass.BUSINESS)

    val flight = Flight(
        flightNumber = "FL123",
        origin = "JFK",
        destination = "LAX",
        departureTime = LocalDateTime.of(2023, 11, 30, 10, 0),
        arrivalTime = LocalDateTime.of(2023, 11, 30, 13, 0),
        airplane = airplane,
        seats = airplane.seatMap.values.toMutableList()
    )

    val flights = mutableListOf(flight)
    val flightService = FlightService(flights)
    val paymentService = PaymentService()
    val notificationService = NotificationService()

    // Search for flights
    val availableFlights = flightService.searchFlights("JFK", "LAX", LocalDateTime.of(2023, 11, 30, 0, 0))
    println("Available flights: ${availableFlights.map { it.flightNumber }}")

    // Create a passenger
    val passenger = Passenger("P123", "Alice", "alice@example.com")

    // Try to book seats
    val reservationResult = flightService.createReservation(flight, passenger, listOf("1A", "1B"))

    if (reservationResult.isSuccess) {
        val reservation = reservationResult.getOrThrow()
        val paymentResult = paymentService.processPayment(reservation, amount = 300.0)

        if (paymentResult.isSuccess) {
            notificationService.sendBookingConfirmation(reservation)
        } else {
            println("Error: ${paymentResult.exceptionOrNull()?.message}")
        }
    } else {
        println("Error: ${reservationResult.exceptionOrNull()?.message}")
    }

    // Cancel reservation if successfully booked
    reservationResult.onSuccess { reservation ->
        val cancellationResult = flightService.cancelReservation(reservation)

        if (cancellationResult.isSuccess) {
            notificationService.sendCancellationConfirmation(reservation)
        } else {
            println("Error: Unable to cancel reservation.")
        }
    }
}
