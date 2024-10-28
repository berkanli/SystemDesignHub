package lld.elevator_system

fun main() {
    // Initialize elevators
    val elevator1 = Elevator(id = 1, maxFloor = 10)
    val elevator2 = Elevator(id = 2, maxFloor = 10)
    val elevatorSystem = ElevatorSystem(listOf(elevator1, elevator2))

    // Add external requests
    elevatorSystem.addRequest(Request(floor = 5, type = RequestType.EXTERNAL, direction = Direction.UP))
    elevatorSystem.addRequest(Request(floor = 2, type = RequestType.EXTERNAL, direction = Direction.UP))
    elevatorSystem.addRequest(Request(floor = 8, type = RequestType.EXTERNAL, direction = Direction.DOWN))
    elevatorSystem.addRequest(Request(floor = 3, type = RequestType.EXTERNAL, direction = Direction.UP))

    // Step through the system multiple times to process requests
    repeat(10) {
        println("=== Step $it ===")
        elevatorSystem.step()
        Thread.sleep(1000)  // Simulate time between steps
    }
}