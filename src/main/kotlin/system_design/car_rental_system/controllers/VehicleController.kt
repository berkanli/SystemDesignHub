package system_design.car_rental_system.controllers

import system_design.car_rental_system.models.Location
import system_design.car_rental_system.models.Vehicle
import system_design.car_rental_system.services.VehicleService

class VehicleController(private val vehicleService: VehicleService) {
    fun addVehicle(vehicle: Vehicle){
        vehicleService.addVehicle(vehicle)
    }

    fun getAvailableVehicles(location: Location): List<Vehicle> {
        return vehicleService.findAvailableVehicles(location)
    }
}