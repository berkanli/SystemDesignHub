package system_design.elevator_system4

import kotlinx.coroutines.delay

class Elevator(private val id: Int) {
    private var currentFloor: Int = 0
    private var elevatorState: ElevatorState = ElevatorState.IDLE

    suspend fun assignInternalRequest(targetFloor: Int) {
        val direction = when {
            currentFloor > targetFloor -> Direction.DOWN
            currentFloor < targetFloor -> Direction.UP
            else -> null
        }

        direction?.let {
            move(Request(targetFloor, it))
        }
    }

    suspend fun move(request: Request) {
        if (currentFloor == request.floor) {
            println("Elevator $id is already on the requested floor ${request.floor}.")
            openDoor()
            closeDoor()
            return
        }

        elevatorState = ElevatorState.MOVING
        val direction = when {
            currentFloor > request.floor -> Direction.DOWN
            currentFloor < request.floor -> Direction.UP
            else -> null
        }

        println("Elevator $id is moving ${direction?.name} to floor ${request.floor}.")

        while (currentFloor != request.floor) {
            currentFloor = if (currentFloor > request.floor) currentFloor - 1 else currentFloor + 1
            println("Elevator $id at floor $currentFloor")
            delay(500) // Coroutine-friendly sleep
        }

        println("Elevator $id has reached floor ${request.floor}.")
        openDoor()
        closeDoor()
        elevatorState = ElevatorState.IDLE
    }

    private suspend fun closeDoor() {
        println("Elevator $id: Closing the door")
        delay(2000) // Simulate door closing delay
    }

    private suspend fun openDoor() {
        println("Elevator $id: Opening the door")
        delay(2000) // Simulate door opening delay
    }

    fun getCurrentFloor(): Int = currentFloor

    fun getState(): ElevatorState = elevatorState
}


