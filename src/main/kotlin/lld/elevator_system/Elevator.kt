package lld.elevator_system

import java.util.PriorityQueue
import kotlin.math.abs

class Elevator(val id: Int, private val maxFloor: Int) {
    var currentFloor: Int = 0
    var direction: Direction = Direction.IDLE
    private val internalRequests = PriorityQueue<Int>{ a, b ->
        val diffA = abs(currentFloor - a)
        val diffB = abs(currentFloor - b)
        diffA - diffB
    }

    fun move() {
        while (internalRequests.isNotEmpty()) {
            val targetFloor = internalRequests.peek()
            direction = if (targetFloor > currentFloor){
                Direction.UP
            } else if(targetFloor < currentFloor){
                Direction.DOWN
            } else {
                Direction.IDLE
            }

            if (currentFloor < targetFloor) currentFloor++
            else if (currentFloor > targetFloor) currentFloor--

            println("Elevator $id is on floor $currentFloor moving $direction")

            if (currentFloor == targetFloor){
                stopAtFloor()
                internalRequests.poll()
            }
        }
        direction = Direction.IDLE
    }

    private fun stopAtFloor() {
        openDoor()
        Thread.sleep(2000)
        closeDoor()
    }

    private fun openDoor() {
        println("Elevator $id: Opening door at floor $currentFloor")
    }

    private fun closeDoor() {
        println("Elevator $id: Closing door at floor $currentFloor")
    }

    private fun updateDirection() {
        direction = if (internalRequests.isEmpty()) {
            Direction.IDLE
        } else {
            if (internalRequests.peek() > currentFloor){
                Direction.UP
            } else {
                Direction.DOWN
            }
        }
    }

    fun canServeRequest(request: Request): Boolean {
        return direction == Direction.IDLE || (direction == request.direction && isFloorInDirection(request.floor))
    }

    private fun isFloorInDirection(floor: Int): Boolean {
        return (direction == Direction.UP && floor >= currentFloor) ||
                (direction == Direction.DOWN && floor <= currentFloor)
    }

    fun addDestination(floor: Int) {
        if (floor in 0..maxFloor) {
            internalRequests.add(floor)
            updateDirection()
        }
    }

    fun getDistanceToFloor(floor: Int): Int {
        return abs(currentFloor - floor)
    }
}
