package system_design.parking_lot

class ParkingLot private constructor (
    private val floors: List<Floor>,
    private val entrances: List<Entrance>
){
    private val tickets = mutableMapOf<String, Ticket>()

    companion object{
        @Volatile
        private var instance: ParkingLot? = null

        fun initialize(floors: List<Floor>, entrances: List<Entrance>){
            synchronized(this){
                if (instance == null){
                    instance = ParkingLot(floors, entrances)
                }
            }
        }
        fun getInstance(): ParkingLot {
            return instance ?: throw IllegalStateException("ParkingLot not initialized. Call initialize() first.")
        }
    }
    
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