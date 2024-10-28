package lld.logging_system

import lld.logging_system.enums.LogLevel

data class LogMessage(
    val timestamp: String,
    val level: LogLevel,
    val message: String,
    val loggerName: String
)