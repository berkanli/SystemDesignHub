package system_design.elevator_system2

import java.util.*

class Elevator(private var currentFloor: Int) {
    private val destinationFloors: Queue<Int> = LinkedList()

    fun nextDestination(): Int? {
        return this.destinationFloors.peek()
    }

    fun currentFloor(): Int {
        return this.currentFloor
    }

    fun popDestination() {
        if (destinationFloors.isNotEmpty()) {
            this.destinationFloors.remove()
        }
    }

    fun addNewDestination(destination: Int) {
        this.destinationFloors.add(destination)
    }

    fun moveUp() {
        currentFloor++
    }

    fun moveDown() {
        currentFloor--
    }

    fun direction(): Direction {
        return if (destinationFloors.isNotEmpty()) {
            when {
                currentFloor < destinationFloors.peek() -> Direction.UP
                currentFloor > destinationFloors.peek() -> Direction.DOWN
                else -> Direction.IDLE
            }
        } else {
            Direction.IDLE
        }
    }

    fun status(): ElevatorStatus {
        return if (destinationFloors.isNotEmpty()) ElevatorStatus.OCCUPIED else ElevatorStatus.EMPTY
    }

    fun hasDestinations(): Boolean {
        return destinationFloors.isNotEmpty()
    }
}