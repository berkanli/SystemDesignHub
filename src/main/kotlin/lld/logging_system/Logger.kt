package lld.logging_system

import lld.logging_system.destinations.LogDestination
import lld.logging_system.enums.LogLevel
import lld.logging_system.filters.LogFilter
import lld.logging_system.formatters.LogFormatter
import java.time.LocalDateTime

class Logger private constructor(
    private val formatter: LogFormatter,
    private val destinations: List<LogDestination>,
    private val filter: LogFilter
){

    companion object{
        @Volatile
        private var instance: Logger? = null

        fun getInstance(formatter: LogFormatter, destinations: List<LogDestination>, filter: LogFilter): Logger {
            return instance ?: synchronized(this) {
                instance ?: Logger(formatter,destinations,filter).also { instance = it }
            }
        }
    }

    private fun log(level: LogLevel, message: String) {
        if (filter.shouldLog(level)) {
            val logMessage = LogMessage(
                timestamp = LocalDateTime.now().toString(),
                level = level,
                message = message,
                loggerName = "DefaultLogger"
            )
            val formattedMessage = formatter.format(logMessage)
            destinations.forEach { it.log(formattedMessage) }
        }
    }

    fun info(message: String) = log(LogLevel.INFO, message)
    fun warn(message: String) = log(LogLevel.WARN, message)
    fun debug(message: String) = log(LogLevel.DEBUG, message)
    fun error(message: String) = log(LogLevel.ERROR, message)
}