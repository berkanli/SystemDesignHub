package lld.car_rental_system.services

import lld.car_rental_system.models.Location
import lld.car_rental_system.models.Vehicle
import lld.car_rental_system.models.enums.VehicleStatus
import lld.car_rental_system.repositories.VehicleRepository

class VehicleService(private val vehicleRepository: VehicleRepository) {

    fun addVehicle(vehicle: Vehicle){
        vehicleRepository.addVehicle(vehicle)
    }

    fun findAvailableVehicles(location: Location): List<Vehicle>{
        return vehicleRepository.findAvailableVehicles(location)
    }

    fun updateVehicleStatus(vehicleId: String, newStatus: VehicleStatus){
        val vehicle = vehicleRepository.findVehicleById(vehicleId)
        if (vehicle != null) {
            vehicle.status = newStatus
            vehicleRepository.updateVehicle(vehicle)
        }
    }
}