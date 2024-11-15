package system_design.car_rental_system.models

import system_design.car_rental_system.models.enums.VehicleStatus

data class Vehicle(
    val vehicleId: String,
    val make: String,
    val model: String,
    val year: Int,
    var status: VehicleStatus,
    val location: Location
)