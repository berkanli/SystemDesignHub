package lld.logging_system

import junit.framework.TestCase.assertTrue
import lld.logging_system.destinations.ConsoleLogger
import lld.logging_system.destinations.FileLogger
import lld.logging_system.enums.LogLevel
import lld.logging_system.filters.LevelLogFilter
import lld.logging_system.formatters.JSONFormatter
import lld.logging_system.formatters.SimpleFormatter
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class LoggerTest {

    private val testFilePath = "test_log.txt"  // Place in the project root or "src/test/test_log.txt"
    private lateinit var outputStream: ByteArrayOutputStream
    private lateinit var originalOut: PrintStream


    @Before
    fun setUp() {
        outputStream = ByteArrayOutputStream()
        originalOut = System.out
        System.setOut(PrintStream(outputStream))
    }

    @After
    fun tearDown() {
        System.setOut(originalOut)
        val testFile = File(testFilePath)
        if (testFile.exists()) testFile.delete()
    }

    @Test
    fun testFileLoggerWithJSONFormatter() {
        val logger = Logger.getInstance(
            formatter = JSONFormatter(),
            destinations = listOf(FileLogger(testFilePath)),
            filter = LevelLogFilter(LogLevel.INFO)
        )

        logger.info("This is an info message.")
        logger.error("This is an error message.")
        // Verifying file creation
        val testFile = File(testFilePath)
        println("Checking file path: ${testFile.absolutePath}")
        assertTrue("Log file should exist", testFile.exists())

        val fileContent = testFile.readText()
        println("File Content: $fileContent")

        assertTrue("FileLogger should contain log level INFO in JSON", fileContent.contains("\"level\": \"INFO\""))
        assertTrue("FileLogger should contain the info message in JSON format", fileContent.contains("\"message\": \"This is an info message.\""))
        assertTrue("FileLogger should contain log level ERROR in JSON", fileContent.contains("\"level\": \"ERROR\""))
        assertTrue("FileLogger should contain the error message in JSON format", fileContent.contains("\"message\": \"This is an error message.\""))
    }

    @Test
    fun testLevelLogFilter() {
        val logger = Logger.getInstance(
            formatter = SimpleFormatter(),
            destinations = listOf(ConsoleLogger()),
            filter = LevelLogFilter(LogLevel.INFO)
        )

        logger.debug("This debug message should not be logged.")
        logger.info("This is an info message.")
        logger.error("This is an error message.")

        val output = outputStream.toString()
        println("Console Output: $output")

        assertTrue("LogFilter should exclude DEBUG level messages", !output.contains("DEBUG"))
        assertTrue("LogFilter should allow INFO level messages", output.contains("INFO"))
        assertTrue("LogFilter should allow ERROR level messages", output.contains("ERROR"))
    }
}