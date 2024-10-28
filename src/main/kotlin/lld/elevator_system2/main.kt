package lld.elevator_system2

fun main() {
    // Initialize the elevator control system with 3 elevators and 10 floors
    val elevatorControlSystem = ElevatorControlSystem(numberOfElevators = 3, numberOfFloors = 10)

    // Add pickup requests at different floors
    elevatorControlSystem.pickUp(3)
    elevatorControlSystem.pickUp(7)
    elevatorControlSystem.pickUp(5)

    // Assign specific destination floors to each elevator for demonstration
    elevatorControlSystem.destination(0, 9)
    elevatorControlSystem.destination(1, 2)
    elevatorControlSystem.destination(2, 8)

    // Run steps to simulate elevator movement
    println("Elevator system initial state:")
    printElevatorStatus(elevatorControlSystem)

    for (i in 1..10) {  // Simulate 10 time steps
        println("\nStep $i:")
        elevatorControlSystem.step()
        printElevatorStatus(elevatorControlSystem)
    }
}

// Helper function to print the status of each elevator
fun printElevatorStatus(elevatorControlSystem: ElevatorControlSystem) {
    elevatorControlSystem.elevators.forEachIndexed { index, elevator ->
        println("Elevator $index: Current Floor = ${elevator.currentFloor()}, Next Destination = ${elevator.nextDestination()}, Direction = ${elevator.direction()}, Status = ${elevator.status()}")
    }
}
