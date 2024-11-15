package system_design.parking_lot2

import system_design.parking_lot2.services.ParkingLot

fun main() {
    // Initialize parking lot with 3 floors, 2 zones per floor, and 5 spaces per zone
    val parkingLot = ParkingLot(floors = 3, zonesPerFloor = 2, spacesPerZone = 5)

    // Vehicle enters and parks
    val ticket1 = parkingLot.parkVehicle("CAR-123", zone = 1)

    // Simulate some parking time
    Thread.sleep(1000)  

    // Vehicle exits and payment is processed
    ticket1?.let {
        parkingLot.exitVehicle(it)
    }
}