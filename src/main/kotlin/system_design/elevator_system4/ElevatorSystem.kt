package system_design.elevator_system4

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*
import kotlin.math.abs

class ElevatorSystem(private val elevators: List<Elevator>) {
    private val requests: Queue<Request> = LinkedList()
    private val mutex = Mutex()

    suspend fun addRequest(request: Request) {
        mutex.withLock {
            requests.add(request)
        }
    }

    suspend fun dispatchRequest() {
        val request = mutex.withLock { requests.poll() } ?: return

        var minDistance = Int.MAX_VALUE
        var target: Elevator? = null

        mutex.withLock {
            for (elevator in elevators) {
                val isAvailable = elevator.getState() == ElevatorState.IDLE
                if (isAvailable) {
                    val distance = abs(request.floor - elevator.getCurrentFloor())
                    if (distance < minDistance) {
                        minDistance = distance
                        target = elevator
                    }
                }
            }
        }

        target?.move(request) ?: println("No available elevator for request: $request")
    }
}