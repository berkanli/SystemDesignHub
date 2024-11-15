package system_design.logging_system.destinations

interface LogDestination {
    fun log(formattedMessage: String)
}