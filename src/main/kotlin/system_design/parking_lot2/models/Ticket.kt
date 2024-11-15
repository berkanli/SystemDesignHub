package system_design.parking_lot2.models

import java.time.LocalDateTime
import java.util.*

data class Ticket(
    val ticketId: String = UUID.randomUUID().toString(),
    val vehicleNumber: String,
    val entryTime: LocalDateTime,
    val spaceId: Int,
    val zone: Int,
    val floor: Int
)
