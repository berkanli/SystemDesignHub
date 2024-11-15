package system_design.atm

class TransferTransaction(
    private val fromAccount: Account,
    private val toAccount: Account,
    amount: Double // Use the amount parameter directly without 'private val'
) : Transaction(fromAccount, amount) {
    override fun execute(): Boolean {
        return if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount)
            TransactionLog.log("Transfer", fromAccount.accountNumber, amount)
            TransactionLog.log("Received Transfer", toAccount.accountNumber, amount)
            true
        } else {
            TransactionLog.log("Failed Transfer", fromAccount.accountNumber, amount)
            false
        }
    }
}
