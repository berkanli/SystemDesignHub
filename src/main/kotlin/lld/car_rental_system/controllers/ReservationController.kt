package lld.car_rental_system.controllers

import lld.car_rental_system.models.Customer
import lld.car_rental_system.models.Location
import lld.car_rental_system.models.RentalReservation
import lld.car_rental_system.models.Vehicle
import lld.car_rental_system.services.ReservationService

class ReservationController(private val reservationService: ReservationService) {

    fun createReservation(
        customer: Customer,
        vehicle: Vehicle,
        pickupLocation: Location,
        dropOffLocation: Location,
        startDate: String,
        endDate: String
    ): RentalReservation {
        return reservationService.createReservation(
            customer,
            vehicle,
            pickupLocation,
            dropOffLocation,
            startDate,
            endDate
        )
    }

    fun completeReservation(reservationId: String) {
        reservationService.completeReservation(reservationId)
    }
}