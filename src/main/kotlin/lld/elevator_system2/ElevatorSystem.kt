package lld.elevator_system2

import java.util.*

class ElevatorControlSystem(private val numberOfElevators: Int, private val numberOfFloors: Int) {
    companion object {
        const val MAX_ELEVATORS = 16
    }

    val elevators: ArrayList<Elevator> = ArrayList()
    private val pickupLocations: Queue<Int> = LinkedList()

    init {
        require(numberOfElevators >= 0) { "Elevator number must be positive" }
        require(numberOfFloors > 0) { "Number of floors must be positive" }

        val effectiveElevators = if (numberOfElevators > MAX_ELEVATORS) MAX_ELEVATORS else numberOfElevators
        initializeElevators(effectiveElevators)
    }

    private fun initializeElevators(effectiveElevators: Int) {
        for (idx in 0 until effectiveElevators) {
            elevators.add(Elevator(1))
        }
    }

    fun pickUp(floor: Int) {
        // Logic to find the closest elevator to the requested floor and add the floor to that elevator's destination
        val closestElevator = elevators.minByOrNull {
            Math.abs(it.currentFloor() - floor)
        }
        closestElevator?.addNewDestination(floor)
    }

    fun destination(elevatorId: Int, destinationFloor: Int) {
        elevators[elevatorId].addNewDestination(destinationFloor)
    }

    fun step() {
        for (elevator in elevators) {
            // Move the elevator if it has a destination
            if (elevator.hasDestinations()) {
                val currentFloor = elevator.currentFloor()
                val nextDestination = elevator.nextDestination() ?: continue // safety check

                if (nextDestination > currentFloor) {
                    elevator.moveUp()
                } else if (nextDestination < currentFloor) {
                    elevator.moveDown()
                }

                // Check if the elevator has reached its next destination
                if (currentFloor == nextDestination) {
                    elevator.popDestination() // Remove the destination once reached
                }
            }
        }
    }


}
