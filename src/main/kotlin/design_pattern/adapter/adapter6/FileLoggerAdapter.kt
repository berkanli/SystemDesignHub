package design_pattern.adapter.adapter6

class FileLoggerAdapter(private val fileLogger: FileLogger): Logger {
    override fun log(message: String) {
        fileLogger.writeLog(message) // Adapting the method
    }
}