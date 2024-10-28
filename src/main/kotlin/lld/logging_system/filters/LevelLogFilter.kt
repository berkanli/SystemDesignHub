package lld.logging_system.filters

import lld.logging_system.enums.LogLevel

class LevelLogFilter(private val minLogLevel: LogLevel): LogFilter {
    override fun shouldLog(level: LogLevel): Boolean {
        return level.priority >= minLogLevel.priority
    }
}