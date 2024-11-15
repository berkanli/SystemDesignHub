package system_design.logging_system.formatters

import system_design.logging_system.LogMessage

interface LogFormatter {
    fun format(message: LogMessage): String
}