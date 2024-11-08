package lld.payment_gateway

class InMemoryTransactionRepository : TransactionRepository {
    private val transactions = mutableMapOf<String, Transaction>()

    override fun saveTransaction(transaction: Transaction): String {
        transactions[transaction.id] = transaction
        return transaction.id
    }

    override fun updateTransactionStatus(transactionId: String, status: PaymentStatus) {
        transactions[transactionId]?.status = status
    }

    override fun getTransaction(transactionId: String): Transaction? {
        return transactions[transactionId]
    }
}