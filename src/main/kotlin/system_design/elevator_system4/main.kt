package system_design.elevator_system4

import kotlinx.coroutines.*

fun main() = runBlocking {
    // Create a list of elevators
    val elevators = listOf(
        Elevator(id = 1),
        Elevator(id = 2),
        Elevator(id = 3)
    )

    // Create an elevator system with the elevators
    val elevatorSystem = ElevatorSystem(elevators)

    // Launch a coroutine to simulate adding external requests
    launch {
        println("Adding external requests to the system...")
        elevatorSystem.addRequest(Request(floor = 5, direction = Direction.UP))
        delay(1000) // Simulate time between requests
        elevatorSystem.addRequest(Request(floor = 2, direction = Direction.DOWN))
        delay(1000)
        elevatorSystem.addRequest(Request(floor = 8, direction = Direction.UP))
        println("Finished adding external requests.")
    }

    // Launch another coroutine to simulate assigning internal requests
    launch {
        delay(4000) // Wait for some external requests to be processed
        println("Assigning internal requests...")
        elevators[0].assignInternalRequest(3) // Assign an internal request to elevator 1
        elevators[1].assignInternalRequest(6) // Assign an internal request to elevator 2
        println("Finished assigning internal requests.")
    }

    // Launch another coroutine to dispatch external requests
    launch {
        println("Dispatching requests...")
        repeat(3) { // Assume 3 external requests are added
            elevatorSystem.dispatchRequest()
            delay(2000) // Simulate processing time
        }
        println("Finished dispatching external requests.")
    }

    // Wait for all coroutines to finish
    delay(15000)
    println("Simulation complete.")
}
