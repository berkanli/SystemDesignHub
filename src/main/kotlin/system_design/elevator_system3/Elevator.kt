package system_design.elevator_system3

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.abs

class Elevator(var id: Int){
    private var currentFloor: Int = 0
    private var state: ElevatorState = ElevatorState.IDLE
    private var direction: Direction = Direction.UP
    private val internalRequests = mutableListOf<Int>()
    private val mutex = Mutex()  // Mutex for safe state management

    suspend fun handleInternalRequest(targetFloor: Int) = coroutineScope {
        mutex.withLock {
            if (targetFloor != currentFloor && !internalRequests.contains(targetFloor)) {
                internalRequests.add(targetFloor)
                if (state == ElevatorState.IDLE) processInternalRequests()
            }
        }
    }

    private suspend fun processInternalRequests() {
        while (internalRequests.isNotEmpty()) {
            state = ElevatorState.MOVING
            val target = internalRequests.minByOrNull { abs(it - currentFloor) } ?: break
            moveToFloor(target)
            internalRequests.remove(target)
            // Simulate door open and close upon arrival
            println("Elevator $id opens doors at floor $currentFloor")
        }
        state = ElevatorState.IDLE
    }

    private suspend fun moveToFloor(targetFloor: Int) {
        while (currentFloor != targetFloor) {
            delay(500)
            currentFloor += if (targetFloor > currentFloor) 1 else -1
            println("Elevator $id at floor $currentFloor")
        }
    }

    fun getState() = state
    fun getFloor() = currentFloor
}
