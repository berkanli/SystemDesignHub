package lld.elevator_system3

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.abs

object ElevatorSystem {
    val elevators: List<Elevator> = List(5) { Elevator(it + 1) }
    private val mutex = Mutex()  // Mutex for safe access to dispatch logic

    suspend fun dispatchExternalRequest(request: Request) {
        if (request.type != RequestType.EXTERNAL) return  // Only handle external requests here

        mutex.withLock {
            var minDistance = Int.MAX_VALUE
            var target: Elevator? = null

            for (elevator in elevators) {
                if (elevator.getState() == ElevatorState.IDLE) {
                    val distance = abs(request.targetFloor - elevator.getFloor())
                    if (distance < minDistance) {
                        minDistance = distance
                        target = elevator
                    }
                }
            }

            target?.let {
                GlobalScope.launch { it.handleInternalRequest(request.targetFloor) }
            } ?: println("No idle elevator available for floor ${request.targetFloor}")
        }
    }
}