package lld.payment_gateway

interface TransactionRepository {
    fun saveTransaction(transaction: Transaction): String
    fun updateTransactionStatus(transactionId: String, status: PaymentStatus)
    fun getTransaction(transactionId: String): Transaction?
}