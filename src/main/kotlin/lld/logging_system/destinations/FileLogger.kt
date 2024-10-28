package lld.logging_system.destinations

import java.io.File

class FileLogger(private val filePath: String) : LogDestination {
    override fun log(formattedMessage: String) {
        File(filePath).appendText(formattedMessage + "\n")
    }
}