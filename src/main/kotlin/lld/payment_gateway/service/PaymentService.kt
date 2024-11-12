package lld.payment_gateway.service

import lld.payment_gateway.model.PaymentRequest
import lld.payment_gateway.model.PaymentResponse
import lld.payment_gateway.PaymentStatus

interface PaymentService {
    fun initiatePayment(request: PaymentRequest): PaymentResponse
    fun validatePayment(request: PaymentRequest): Boolean
    fun processPayment(transactionId: String): PaymentStatus
    fun getTransactionStatus(transactionId: String): PaymentStatus
}