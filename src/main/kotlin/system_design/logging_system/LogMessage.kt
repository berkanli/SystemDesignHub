package system_design.logging_system

import system_design.logging_system.enums.LogLevel

data class LogMessage(
    val timestamp: String,
    val level: LogLevel,
    val message: String,
    val loggerName: String
)