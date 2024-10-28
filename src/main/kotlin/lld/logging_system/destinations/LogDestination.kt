package lld.logging_system.destinations

interface LogDestination {
    fun log(formattedMessage: String)
}