package lld.car_rental_system.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateUtils {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // Parse a string into a LocalDate
    fun parseDate(dateStr: String): LocalDate? {
        return try {
            LocalDate.parse(dateStr, dateFormatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    // Format a LocalDate into a string
    fun formatDate(date: LocalDate): String {
        return date.format(dateFormatter)
    }
}