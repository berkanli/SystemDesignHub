package lld.logging_system.destinations

class ConsoleLogger: LogDestination {
    override fun log(formattedMessage: String) {
        println(formattedMessage)
    }
}