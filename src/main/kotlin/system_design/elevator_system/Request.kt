package system_design.elevator_system

data class Request(val floor: Int, val type: RequestType, val direction: Direction? = null)
