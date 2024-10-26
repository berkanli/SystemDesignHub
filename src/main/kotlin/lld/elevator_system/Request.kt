package lld.elevator_system

data class Request(val floor: Int, val type: RequestType, val direction: Direction? = null)