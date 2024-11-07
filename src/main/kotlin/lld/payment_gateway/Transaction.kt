package lld.payment_gateway

import java.time.LocalDateTime
import java.util.*

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val amount: Double,
    val currency: String,
    val sourceAccountId: String,
    val destinationAccountId: String,
    var status: PaymentStatus,
    val createdAt: LocalDateTime = LocalDateTime.now()
)