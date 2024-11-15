package system_design.car_rental_system.repositories

import system_design.car_rental_system.models.Location
import system_design.car_rental_system.models.Vehicle
import system_design.car_rental_system.models.enums.VehicleStatus

class VehicleRepository {
    private val vehicles = mutableListOf<Vehicle>()

    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    fun findAvailableVehicles(location: Location): List<Vehicle> {
        return vehicles.filter { it.status == VehicleStatus.AVAILABLE && it.location == location }
    }

    fun findVehicleById(vehicleId: String): Vehicle? {
        return vehicles.find { it.vehicleId == vehicleId }
    }

    fun updateVehicle(vehicle: Vehicle) {
        val index = vehicles.indexOfFirst { it.vehicleId == vehicle.vehicleId }
        if (index != -1) {
            vehicles[index] = vehicle
        }
    }
}