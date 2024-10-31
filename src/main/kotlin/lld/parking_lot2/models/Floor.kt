package lld.parking_lot2.models

import java.util.concurrent.ConcurrentHashMap

class Floor(val level: Int, val zones: Int, spacesPerZone: Int) {
    private val zoneMap: ConcurrentHashMap<Int, ConcurrentHashMap<Int, ParkingSpace>> = ConcurrentHashMap()

    init {
        for (zone in 1..zones) {
            val spaceMap = ConcurrentHashMap<Int, ParkingSpace>()
            for (i in 1..spacesPerZone) {
                spaceMap[i] = ParkingSpace(id = i, zone = zone, type = SpaceType.REGULAR)
            }
            zoneMap[zone] = spaceMap
        }
    }

    // Find and allocate an available space in a zone
    fun allocateSpace(zone: Int): ParkingSpace? {
        val spaces = zoneMap[zone] ?: return null
        for (space in spaces.values) {
            if (space.isAvailable) {
                space.isAvailable = false
                return space
            }
        }
        return null // No available spaces in the zone
    }

    // Free up a space in a specific zone
    fun freeSpace(spaceId: Int, zone: Int) {
        zoneMap[zone]?.get(spaceId)?.isAvailable = true
    }
}