package system_design.logging_system.filters

import system_design.logging_system.enums.LogLevel

class LevelLogFilter(private val minLogLevel: LogLevel): LogFilter {
    override fun shouldLog(level: LogLevel): Boolean {
        return level.priority >= minLogLevel.priority
    }
}