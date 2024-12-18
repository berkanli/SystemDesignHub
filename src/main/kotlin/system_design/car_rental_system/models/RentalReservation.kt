package system_design.car_rental_system.models

import system_design.car_rental_system.models.enums.ReservationStatus

data class RentalReservation(
    val reservationId: String,
    val customer: Customer,
    val vehicle: Vehicle,
    val pickupLocation: Location,
    val dropOffLocation: Location,
    val startDate: String,
    val endDate: String,
    var status: ReservationStatus
)