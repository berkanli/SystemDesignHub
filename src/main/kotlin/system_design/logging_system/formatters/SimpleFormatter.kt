package system_design.logging_system.formatters

import system_design.logging_system.LogMessage

class SimpleFormatter: LogFormatter {
    override fun format(message: LogMessage): String {
        return "${message.timestamp} [${message.level}] ${message.loggerName}: ${message.message}"
    }
}