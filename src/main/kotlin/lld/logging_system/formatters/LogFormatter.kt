package lld.logging_system.formatters

import lld.logging_system.LogMessage

interface LogFormatter {
    fun format(message: LogMessage): String
}