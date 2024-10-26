package lld.elevator_system

class ElevatorSystem(private val elevators: List<Elevator>) {
    private val pendingRequests: MutableList<Request> = mutableListOf()

    fun addRequest(request: Request) {
        pendingRequests.add(request)
    }

    fun step() {
        val iterator = pendingRequests.iterator()
        while (iterator.hasNext()){
            val request = iterator.next()
            val targetElevator = elevators
                .filter { it.direction == Direction.IDLE || it.canServeRequest(request) }
                .minByOrNull { it.getDistanceToFloor(request.floor) }

            if (targetElevator != null) {
                targetElevator.addDestination(request.floor)
                iterator.remove()
            }
        }

        elevators.forEach { it.move() }
    }

}