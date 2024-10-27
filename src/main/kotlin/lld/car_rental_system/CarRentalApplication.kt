package lld.car_rental_system

import lld.car_rental_system.controllers.PaymentController
import lld.car_rental_system.controllers.ReservationController
import lld.car_rental_system.controllers.VehicleController
import lld.car_rental_system.models.Customer
import lld.car_rental_system.models.Location
import lld.car_rental_system.models.Vehicle
import lld.car_rental_system.models.enums.VehicleStatus
import lld.car_rental_system.repositories.PaymentRepository
import lld.car_rental_system.repositories.ReservationRepository
import lld.car_rental_system.repositories.VehicleRepository
import lld.car_rental_system.services.PaymentService
import lld.car_rental_system.services.ReservationService
import lld.car_rental_system.services.VehicleService

fun main() {
    // Initialize repositories
    val vehicleRepository = VehicleRepository()
    val reservationRepository = ReservationRepository()
    val paymentRepository = PaymentRepository()

    // Initialize services with repository dependencies
    val vehicleService = VehicleService(vehicleRepository)
    val reservationService = ReservationService(vehicleService, reservationRepository)
    val paymentService = PaymentService(paymentRepository)

    // Initialize controllers
    val vehicleController = VehicleController(vehicleService)
    val reservationController = ReservationController(reservationService)
    val paymentController = PaymentController(paymentService)

    // Sample data setup
    val location = Location("L1", "123 Main St", "Cityville", "State", "12345")
    val vehicle1 = Vehicle("V1", "Toyota", "Camry", 2022, VehicleStatus.AVAILABLE, location)
    val vehicle2 = Vehicle("V2", "Honda", "Civic", 2021, VehicleStatus.AVAILABLE, location)
    val customer = Customer("C1", "Alice", "alice@example.com", "555-1234", "D1234567")

    // Add vehicles to inventory
    vehicleController.addVehicle(vehicle1)
    vehicleController.addVehicle(vehicle2)

    // Check available vehicles at a specific location
    val availableVehicles = vehicleController.getAvailableVehicles(location)
    println("Available vehicles at ${location.address}: $availableVehicles")

    // Create a reservation for the first available vehicle
    val reservation = reservationController.createReservation(
        customer = customer,
        vehicle = availableVehicles.first(),
        pickupLocation = location,
        dropOffLocation = location,
        startDate = "2023-12-01",
        endDate = "2023-12-10"
    )
    println("Reservation created: $reservation")

    // Process a payment for the reservation
    val payment = paymentController.makePayment(300.0, reservation.reservationId)
    println("Payment processed: $payment")

    // Retrieve payments for the reservation
    val payments = paymentController.getPaymentsForReservation(reservation.reservationId)
    println("Payments for reservation ${reservation.reservationId}: $payments")

    // Complete the reservation
    reservationController.completeReservation(reservation.reservationId)
    println("Reservation ${reservation.reservationId} completed.")
}
