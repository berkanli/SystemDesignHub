package lld.parking_lot

class Entrance(val entranceId: String) {

    fun findNearestAvailableSpot(floor: Floor, vehicleType: VehicleType): ParkingSpace? {
        return floor.getAvailableSpace(vehicleType, entranceId)
    }
}