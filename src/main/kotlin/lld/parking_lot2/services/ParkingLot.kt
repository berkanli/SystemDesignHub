package lld.parking_lot2.services

import lld.parking_lot2.models.Floor
import lld.parking_lot2.models.Ticket
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ParkingLot(val floors: Int, zonesPerFloor: Int, spacesPerZone: Int) {
    private val floorMap: ConcurrentHashMap<Int, Floor> = ConcurrentHashMap()
    private val lock = ReentrantLock()

    init {
        for (level in 1..floors) {
            floorMap[level] = Floor(level, zonesPerFloor, spacesPerZone)
        }
    }

    // Handle vehicle entry, allocate space, and generate ticket
    fun parkVehicle(vehicleNumber: String, zone: Int): Ticket? {
        lock.withLock {
            for (floor in floorMap.values) {
                val space = floor.allocateSpace(zone)
                if (space != null) {
                    val ticket = Ticket(
                        vehicleNumber = vehicleNumber,
                        entryTime = LocalDateTime.now(),
                        spaceId = space.id,
                        zone = zone,
                        floor = floor.level
                    )
                    println("Vehicle parked at floor ${floor.level}, zone $zone, space ${space.id}")
                    return ticket
                }
            }
            println("No available space in zone $zone")
            return null
        }
    }

    // Handle vehicle exit, calculate payment, and free up space
    fun exitVehicle(ticket: Ticket): Boolean {
        lock.withLock {
            floorMap[ticket.floor]?.freeSpace(ticket.spaceId, ticket.zone)
            val payment = PaymentGateway.processPayment(ticket)
            println("Vehicle exited. Ticket ID: ${ticket.ticketId}, Payment: $${payment}")
            return true
        }
    }
}