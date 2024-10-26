package lld.elevator_system

fun main() {
    val elevatorSystem = ElevatorSystem(
        listOf(
            Elevator(1, maxFloor = 10),
            Elevator(2, maxFloor = 10),
            Elevator(3, maxFloor = 10)
        )
    )

    elevatorSystem.addRequest(Request(floor = 5, type = RequestType.EXTERNAL, direction = Direction.UP))
    elevatorSystem.addRequest(Request(floor = 8, type = RequestType.EXTERNAL, direction = Direction.DOWN))
    elevatorSystem.addRequest(Request(floor = 2, type = RequestType.INTERNAL))
    elevatorSystem.addRequest(Request(floor = 7, type = RequestType.EXTERNAL, direction = Direction.UP))
    elevatorSystem.addRequest(Request(floor = 4, type = RequestType.INTERNAL))

    repeat(15) {
        elevatorSystem.step()
    }
}