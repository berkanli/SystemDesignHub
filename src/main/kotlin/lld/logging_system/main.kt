package lld.logging_system

import lld.logging_system.destinations.ConsoleLogger
import lld.logging_system.enums.LogLevel
import lld.logging_system.filters.LevelLogFilter
import lld.logging_system.formatters.JSONFormatter

fun main() {
    val formatter = JSONFormatter()
    val destinations = listOf(ConsoleLogger())
    val filter = LevelLogFilter(LogLevel.INFO)

    val logger = Logger.getInstance(
        formatter = formatter,
        destinations = destinations,
        filter = filter
    )

    logger.info("Application started.")
    logger.debug("Debugging mode enabled.") // Won't be logged due to filter
    logger.error("An error occurred.")
}