package system_design.elevator_system

class DoorController(private val elevatorId: Int) {
    fun openDoor(currentFloor: Int) {
        println("Elevator $elevatorId: Opening door at floor $currentFloor")
    }

    fun closeDoor(currentFloor: Int) {
        println("Elevator $elevatorId: Closing door at floor $currentFloor")
    }
}