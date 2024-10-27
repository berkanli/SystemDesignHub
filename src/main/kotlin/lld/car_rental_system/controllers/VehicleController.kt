package lld.car_rental_system.controllers

import lld.car_rental_system.models.Location
import lld.car_rental_system.models.Vehicle
import lld.car_rental_system.services.VehicleService

class VehicleController(private val vehicleService: VehicleService) {
    fun addVehicle(vehicle: Vehicle){
        vehicleService.addVehicle(vehicle)
    }

    fun getAvailableVehicles(location: Location): List<Vehicle> {
        return vehicleService.findAvailableVehicles(location)
    }
}