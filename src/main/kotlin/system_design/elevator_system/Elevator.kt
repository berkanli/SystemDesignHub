package system_design.elevator_system

class Elevator(val id: Int, maxFloor: Int) {
    private val doorController = DoorController(id)
    val requestManager = RequestManager(maxFloor)
    val movementController = MovementController(id, maxFloor)

    val direction: Direction
        get() = movementController.direction

    val currentFloor: Int
        get() = movementController.currentFloor

    fun addDestination(floor: Int) {
        requestManager.addRequest(floor, currentFloor)
    }

    fun serveRequests() {
        // While the elevator has requests, keep serving
        while (requestManager.hasRequest()) {
            val targetFloor = requestManager.getNextRequest()
            if (targetFloor != null) {
                // Move directly to the target floor
                movementController.moveToFloor(targetFloor)
                doorController.openDoor(currentFloor)
                Thread.sleep(2000)  // Wait for the door to open
                doorController.closeDoor(currentFloor)
            }
        }
    }

    // Method to check if elevator can serve request
    fun canServeRequest(request: Request): Boolean {
        return direction == Direction.IDLE || (direction == request.direction && isFloorInDirection(request.floor))
    }

    private fun isFloorInDirection(floor: Int): Boolean {
        return (direction == Direction.UP && floor >= currentFloor) || (direction == Direction.DOWN && floor <= currentFloor)
    }
}