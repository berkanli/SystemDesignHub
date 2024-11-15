package system_design.logging_system.formatters

import system_design.logging_system.LogMessage

class JSONFormatter: LogFormatter {
    override fun format(message: LogMessage): String {
        return """{
            "timestamp": "${message.timestamp}",
            "level": "${message.level}",
            "logger": "${message.loggerName}",
            "message": "${message.message}"
        }"""
    }
}