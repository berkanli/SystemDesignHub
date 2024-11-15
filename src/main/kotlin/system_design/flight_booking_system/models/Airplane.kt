package system_design.flight_booking_system.models

data class Airplane(
    val model: String,
    val capacity: Int,
    val seatMap: MutableMap<String, Seat> = mutableMapOf()
)
