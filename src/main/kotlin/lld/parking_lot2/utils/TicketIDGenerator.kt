package lld.parking_lot2.utils

fun generateTicketId(): String {
    return "TICKET-${System.currentTimeMillis()}"
}