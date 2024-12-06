package design_pattern.adapter.adapter6

class ConsoleLogger: Logger {
    override fun log(message: String) {
        println("ConsoleLogger: $message")
    }
}