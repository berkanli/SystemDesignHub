package system_design.car_rental_system.services

import system_design.car_rental_system.models.Customer
import system_design.car_rental_system.models.Location
import system_design.car_rental_system.models.RentalReservation
import system_design.car_rental_system.models.Vehicle
import system_design.car_rental_system.models.enums.ReservationStatus
import system_design.car_rental_system.models.enums.VehicleStatus
import system_design.car_rental_system.repositories.ReservationRepository
import system_design.car_rental_system.utils.DateUtils

class ReservationService(
    private val vehicleService: VehicleService,
    private val reservationRepository: ReservationRepository
) {

    fun createReservation(
        customer: Customer,
        vehicle: Vehicle,
        pickupLocation: Location,
        dropOffLocation: Location,
        startDate: String,
        endDate: String
    ): RentalReservation {
        // Validate and parse dates
        val parsedStartDate = DateUtils.parseDate(startDate)
        val parsedEndDate = DateUtils.parseDate(endDate)

        if (parsedStartDate == null || parsedEndDate == null || parsedStartDate.isAfter(parsedEndDate)) {
            throw IllegalArgumentException("Invalid dates provided for reservation")
        }

        val reservationId = generateReservationId()

        val reservation = RentalReservation(
            reservationId = reservationId,
            customer = customer,
            vehicle = vehicle,
            pickupLocation = pickupLocation,
            dropOffLocation = dropOffLocation,
            startDate = startDate,
            endDate = endDate,
            status = ReservationStatus.ACTIVE
        )

        // Update vehicle status to RESERVED
        vehicleService.updateVehicleStatus(vehicle.vehicleId, VehicleStatus.RESERVED)

        // Save the reservation in the repository
        reservationRepository.addReservation(reservation)

        return reservation
    }

    private fun generateReservationId(): String {
        return "R${reservationRepository.size + 1}"
    }

    fun completeReservation(reservationId: String) {
        val reservation = reservationRepository.findReservationById(reservationId)
        reservation?.let {
            it.status = ReservationStatus.COMPLETED
            vehicleService.updateVehicleStatus(it.vehicle.vehicleId, VehicleStatus.AVAILABLE)
            reservationRepository.updateReservation(it)
        }
    }
}