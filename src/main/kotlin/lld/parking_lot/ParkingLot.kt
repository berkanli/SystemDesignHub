package lld.parking_lot

class ParkingLot(private val floors: List<Floor>, private val entrances: List<Entrance>){
    private val tickets = mutableMapOf<String, Ticket>()

    fun addVehicle(vehicle: Vehicle, entranceId: String): Ticket? {
        val entrance = entrances.find { it.entranceId == entranceId } ?: return null
        for (floor in floors) {
            val closestSpot = entrance.findNearestAvailableSpot(floor, vehicle.vehicleType)
            if (closestSpot != null) {
                floor.occupySpace(closestSpot)  // Mark the spot as occupied
                val ticket = Ticket(generateTicketId(), vehicle, System.currentTimeMillis(), closestSpot)
                tickets[ticket.ticketId] = ticket
                return ticket
            }
        }
        return null
    }

    fun removeVehicle(ticketId: String): Double? {
        val ticket = tickets[ticketId] ?: return null
        val floor = floors.find { it.spaces.contains(ticket.parkingSpace) }
        floor?.freeSpace(ticket.parkingSpace)
        ticket.exitTime = System.currentTimeMillis()
        val fee = PaymentProcessor.calculateFee(ticket)
        tickets.remove(ticketId)
        return fee
    }

    private fun generateTicketId(): String {
        return "TICKET-${System.currentTimeMillis()}"
    }
}