package system_design.flight_booking_system

import system_design.flight_booking_system.models.Airplane
import system_design.flight_booking_system.models.Flight
import system_design.flight_booking_system.models.Passenger
import system_design.flight_booking_system.models.Seat
import system_design.flight_booking_system.models.enums.SeatClass
import system_design.flight_booking_system.services.FlightService
import system_design.flight_booking_system.services.NotificationService
import system_design.flight_booking_system.services.PaymentService
import java.time.LocalDateTime

fun main() {
    val airplane = Airplane("Boeing 737", 180)

    // Initialize flight and assign airplane seats to the flight
    val flight = Flight(
        flightNumber = "FL123",
        origin = "JFK",
        destination = "LAX",
        departureTime = LocalDateTime.of(2023, 11, 30, 10, 0),
        arrivalTime = LocalDateTime.of(2023, 11, 30, 13, 0),
        airplane = airplane,
        seats = initializeSeatsForFlight()
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

fun initializeSeatsForFlight(): MutableList<Seat> {
    return mutableListOf(
        Seat("1A", SeatClass.ECONOMY),
        Seat("1B", SeatClass.ECONOMY),
        Seat("1C", SeatClass.BUSINESS)
    )
}
