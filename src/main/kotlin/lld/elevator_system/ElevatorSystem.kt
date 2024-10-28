package lld.elevator_system

class ElevatorSystem(private val elevators: List<Elevator>) {
    private val pendingRequests: MutableList<Request> = mutableListOf()

    fun addRequest(request: Request) {
        pendingRequests.add(request)
    }

    fun step() {
        // Process external requests
        val iterator = pendingRequests.iterator()
        while (iterator.hasNext()) {
            val request = iterator.next()
            // Find the closest available elevator to serve this request
            val targetElevator = elevators
                .filter { it.direction == Direction.IDLE || it.canServeRequest(request) }
                .minByOrNull { Math.abs(it.currentFloor - request.floor) } // Find the nearest elevator

            if (targetElevator != null) {
                targetElevator.addDestination(request.floor)
                println("Passenger boarded Elevator ${targetElevator.id} and requested floor ${request.floor}.")
                iterator.remove()

                // Simulate a passenger making an internal request after boarding
                val internalRequestFloor = (1..10).random()  // Random floor from 1 to 10
                targetElevator.addDestination(internalRequestFloor)
                println("Passenger in Elevator ${targetElevator.id} requested internal floor $internalRequestFloor.")
            }
        }

        // Serve requests for each elevator independently
        elevators.forEach { elevator ->
            elevator.serveRequests()  // Each elevator serves its own requests
        }
    }
}