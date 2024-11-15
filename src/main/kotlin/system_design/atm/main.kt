package system_design.atm

fun main() {
    val bank = Bank
    val atm = ATM(bank)

    // Test Data Setup
    val account1 = SavingsAccount("12345", 1000.0)
    val card1 = Card("1111", account1, "1234")
    bank.addAccount(account1, card1)

    val account2 = SavingsAccount("67890", 500.0)
    val card2 = Card("2222", account2, "5678")
    bank.addAccount(account2, card2)

    // Authenticate with account1's card
    if (atm.startTransaction(card1, "1234")) {
        // Perform Transfer from account1 to account2
        println("\n--- Transfer Test ---")
        println("Balance before transfer - Account 12345: ${account1.balance}")
        println("Balance before transfer - Account 67890: ${account2.balance}")

        atm.performTransfer(account1, account2, 300.0) // Transfer $300 from account1 to account2

        println("Balance after transfer - Account 12345: ${account1.balance}")
        println("Balance after transfer - Account 67890: ${account2.balance}")

        atm.endTransaction()
    }

    // Display all transaction logs
    println("\n--- Transaction Log ---")
    TransactionLog.getLogs().forEach { println(it) }
}
