package lld.parking_lot

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ParkingLotTest {
    @Before
    fun setUp() {
        // Define parking spaces with distances from entrances
        val space1 = ParkingSpace("S1", VehicleType.MEDIUM, mapOf("E1" to 10, "E2" to 15))
        val space2 = ParkingSpace("S2", VehicleType.MEDIUM, mapOf("E1" to 20, "E2" to 5))
        val space3 = ParkingSpace("S3", VehicleType.LARGE, mapOf("E1" to 30, "E2" to 25))

        // Create a floor with parking spaces
        val floor1 = Floor(1, mutableListOf(space1, space2, space3))

        // Set up entrances
        val entrance1 = Entrance("E1")
        val entrance2 = Entrance("E2")

        // Reinitialize the ParkingLot singleton with floors and entrances before each test
        ParkingLot.initialize(listOf(floor1), listOf(entrance1, entrance2))
    }

    @Test
    fun testAddVehicleClosestSpotFromEntrance() {
        val parkingLot = ParkingLot.getInstance()
        val vehicle = Vehicle("ABC123", VehicleType.MEDIUM)

        // Add vehicle entering from entrance E1
        val ticket = parkingLot.addVehicle(vehicle, "E1")

        // Verify that a ticket is issued and the closest spot to E1 (space S1) is assigned
        assertNotNull(ticket)
        assertEquals("S1", ticket?.parkingSpace?.spaceId)
    }

    @Test
    fun testAddVehicleFromDifferentEntrance() {
        val parkingLot = ParkingLot.getInstance()
        val vehicle = Vehicle("XYZ789", VehicleType.MEDIUM)

        // Add vehicle entering from entrance E2
        val ticket = parkingLot.addVehicle(vehicle, "E2")!!

        // Verify that a ticket is issued and the closest spot to E2 (space S2) is assigned
        assertNotNull(ticket)
        assertEquals("S2", ticket.parkingSpace.spaceId)
    }

    @Test
    fun testRemoveVehicleAndFreeSpot() {
        val parkingLot = ParkingLot.getInstance()
        val vehicle = Vehicle("ABC123", VehicleType.MEDIUM)
        val ticket = parkingLot.addVehicle(vehicle, "E1")
        assertNotNull(ticket)

        // Remove vehicle
        val fee = parkingLot.removeVehicle(ticket?.ticketId.toString())

        // Check if fee is calculated and the spot is freed
        assertNotNull(fee)
        assertEquals(false, ticket?.parkingSpace?.isOccupied)
    }

    @Test
    fun testNoAvailableSpot() {
        val parkingLot = ParkingLot.getInstance()
        val vehicle1 = Vehicle("ABC123", VehicleType.MEDIUM)
        val vehicle2 = Vehicle("DEF456", VehicleType.MEDIUM)
        val vehicle3 = Vehicle("GHI789", VehicleType.MEDIUM)

        // Fill up all MEDIUM spots
        parkingLot.addVehicle(vehicle1, "E1")
        parkingLot.addVehicle(vehicle2, "E1")

        // Try to add another car when no spots are available
        val ticket = parkingLot.addVehicle(vehicle3, "E1")
        assertNull(ticket)
    }

    @Test
    fun testAssignDifferentSpotForDifferentVehicleType() {
        val parkingLot = ParkingLot.getInstance()
        val vehicle = Vehicle("LMN456", VehicleType.LARGE)

        // Add a large vehicle, which should get the large spot (space S3)
        val ticket = parkingLot.addVehicle(vehicle, "E1")

        assertNotNull(ticket)
        assertEquals("S3", ticket?.parkingSpace?.spaceId)
    }
}
