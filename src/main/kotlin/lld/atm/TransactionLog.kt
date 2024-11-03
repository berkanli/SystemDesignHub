package lld.atm

import java.time.LocalDateTime

// TransactionLog for keeping a log of all transactions
object TransactionLog {
    private val logs = mutableListOf<String>()

    // Method to add a log entry
    fun log(type: String, accountNumber: String, amount: Double) {
        val logEntry = "${LocalDateTime.now()} - $type of $$amount for Account $accountNumber"
        logs.add(logEntry)
        println(logEntry) // Print log entry for visibility during tests
    }

    // Method to retrieve all logs
    fun getLogs(): List<String> {
        return logs
    }
}
