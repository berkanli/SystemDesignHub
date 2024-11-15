package system_design.atm

class ATM(private val bank: Bank) {
    private var currentTransaction: Transaction? = null

    // Start a transaction by authenticating the card and PIN
    fun startTransaction(card: Card, pin: String): Boolean {
        val authenticated = bank.authenticate(card, pin)
        if (authenticated) {
            println("Authentication successful.")
        } else {
            println("Authentication failed.")
        }
        return authenticated
    }

    // Perform a withdrawal transaction
    fun performWithdrawal(account: Account, amount: Double): Boolean {
        val transaction = WithdrawalTransaction(account, amount)
        val success = transaction.execute()
        if (success) {
            println("Withdrawal of $$amount successful. New balance: ${account.balance}")
        } else {
            println("Withdrawal of $$amount failed. Insufficient funds.")
        }
        return success
    }

    // Perform a deposit transaction
    fun performDeposit(account: Account, amount: Double): Boolean {
        val transaction = DepositTransaction(account, amount)
        val success = transaction.execute()
        if (success) {
            println("Deposit of $$amount successful. New balance: ${account.balance}")
        } else {
            println("Deposit of $$amount failed.")
        }
        return success
    }

    // Perform a transfer transaction
    fun performTransfer(fromAccount: Account, toAccount: Account, amount: Double): Boolean {
        val transaction = TransferTransaction(fromAccount, toAccount, amount)
        val success = transaction.execute()
        if (success) {
            println("Transfer of $$amount from Account ${fromAccount.accountNumber} to Account ${toAccount.accountNumber} successful.")
        } else {
            println("Transfer of $$amount from Account ${fromAccount.accountNumber} to Account ${toAccount.accountNumber} failed due to insufficient funds.")
        }
        return success
    }

    // End the current transaction
    fun endTransaction() {
        currentTransaction = null
        println("Transaction ended.")
    }
}

