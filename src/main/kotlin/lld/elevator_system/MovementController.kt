package lld.elevator_system

class MovementController(private val elevatorId: Int, private val maxFloor: Int) {
    var currentFloor = 0
        private set
    var direction: Direction = Direction.IDLE
        private set

    fun moveToFloor(targetFloor: Int){
        direction = when{
            targetFloor > currentFloor -> Direction.UP
            targetFloor < currentFloor -> Direction.DOWN
            else -> Direction.IDLE
        }

        while (currentFloor != targetFloor){
            currentFloor += if (direction == Direction.UP) 1 else -1
            println("Elevator $elevatorId is on floor $currentFloor moving $direction")
            Thread.sleep(500)
        }

        direction = Direction.IDLE
    }
}