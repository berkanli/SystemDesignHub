package lld.parking_lot

data class ParkingSpace(
    val spaceId: String,
    val spotType: VehicleType,
    val distanceFromEntrances: Map<String, Int>
){
    var isOccupied: Boolean = false
}
