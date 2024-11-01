package lld.elevator_system3

data class Request(val targetFloor: Int, val direction: Direction, val type: RequestType)