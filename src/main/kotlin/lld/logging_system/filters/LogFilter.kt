package lld.logging_system.filters

import lld.logging_system.enums.LogLevel

interface LogFilter {
    fun shouldLog(level: LogLevel): Boolean
}

