package system_design.parking_lot2.utils

fun generateTicketId(): String {
    return "TICKET-${System.currentTimeMillis()}"
}