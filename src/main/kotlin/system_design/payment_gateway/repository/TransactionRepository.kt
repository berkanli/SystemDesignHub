package system_design.payment_gateway.repository

import system_design.payment_gateway.PaymentStatus
import system_design.payment_gateway.model.Transaction

interface TransactionRepository {
    fun saveTransaction(transaction: Transaction): String
    fun updateTransactionStatus(transactionId: String, status: PaymentStatus)
    fun getTransaction(transactionId: String): Transaction?
}