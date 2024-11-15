package system_design.parking_lot

class Floor(
    val floorNumber: Int,
    val spaces: MutableList<ParkingSpace>
) {
    fun getAvailableSpace(vehicleType: VehicleType, entranceId: String): ParkingSpace? {
        return spaces
            .filter { it.spotType == vehicleType && !it.isOccupied }
            .minByOrNull { it.distanceFromEntrances[entranceId] ?: Int.MAX_VALUE }
    }

    fun occupySpace(space: ParkingSpace) {
        space.isOccupied = true
    }

    fun freeSpace(space: ParkingSpace) {
        space.isOccupied = false
    }
}