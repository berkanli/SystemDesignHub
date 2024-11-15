package system_design.logging_system.destinations

class ConsoleLogger: LogDestination {
    override fun log(formattedMessage: String) {
        println(formattedMessage)
    }
}