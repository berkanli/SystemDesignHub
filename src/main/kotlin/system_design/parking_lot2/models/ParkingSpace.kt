package system_design.parking_lot2.models

data class ParkingSpace(
    val id: Int,
    val zone: Int,
    val type: SpaceType,
    var isAvailable: Boolean = true
)