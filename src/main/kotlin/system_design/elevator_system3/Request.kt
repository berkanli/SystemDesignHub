package system_design.elevator_system3

data class Request(val targetFloor: Int, val direction: Direction, val type: RequestType)