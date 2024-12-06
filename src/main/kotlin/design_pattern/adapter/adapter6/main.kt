package design_pattern.adapter.adapter6

fun main() {
    val consoleLogger = ConsoleLogger()
    consoleLogger.log("This is a message to the console")

    val fileLogger = FileLogger()
    val adapter = FileLoggerAdapter(fileLogger)
    adapter.log("This is a message to a file")
}