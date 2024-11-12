package lld.payment_gateway.repository

import lld.payment_gateway.PaymentStatus
import lld.payment_gateway.model.Transaction

interface TransactionRepository {
    fun saveTransaction(transaction: Transaction): String
    fun updateTransactionStatus(transactionId: String, status: PaymentStatus)
    fun getTransaction(transactionId: String): Transaction?
}