package system_design.parking_lot

data class Ticket(
    val ticketId: String,
    val vehicle: Vehicle,
    val entryTime: Long,
    val parkingSpace: ParkingSpace,
    var exitTime: Long? = null
)