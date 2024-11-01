package lld.elevator_system3

import kotlinx.coroutines.delay

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun main() = coroutineScope {
    // Coroutine to simulate external requests
    launch {
        repeat(10) {  // Simulate 10 external requests
            val floor = Random.nextInt(1, 10)
            val direction = if (Random.nextBoolean()) Direction.UP else Direction.DOWN
            val request = Request(targetFloor = floor, direction = direction, type = RequestType.EXTERNAL)
            println("External request: Floor $floor, Direction $direction")
            ElevatorSystem.dispatchExternalRequest(request)
            delay(1000)  // Delay between requests for realism
        }
    }

    // Coroutine to simulate internal requests for each elevator
    launch {
        repeat(5) {  // 5 rounds of internal requests across all elevators
            delay(2000)  // Delay to allow some external requests to be processed
            ElevatorSystem.elevators.forEach { elevator ->
                if (elevator.getState() == ElevatorState.MOVING) {
                    val targetFloor = Random.nextInt(1, 10)
                    println("Internal request from Elevator ${elevator.id} to Floor $targetFloor")
                    elevator.handleInternalRequest(targetFloor)
                }
            }
        }
    }

    // Monitor the system state every few seconds
    launch {
        while (isActive) {
            delay(3000)
            println("\n--- System Status ---")
            ElevatorSystem.elevators.forEach { elevator ->
                println("Elevator ${elevator.id} - Floor: ${elevator.getFloor()}, State: ${elevator.getState()}")
            }
            println("---------------------\n")
        }
    }

    delay(15000)  // Run simulation for 15 seconds
    println("Simulation completed.")
}
