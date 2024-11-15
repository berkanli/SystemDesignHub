package system_design.logging_system.filters

import system_design.logging_system.enums.LogLevel

interface LogFilter {
    fun shouldLog(level: LogLevel): Boolean
}

