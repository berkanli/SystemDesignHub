package system_design.payment_gateway.service

import system_design.payment_gateway.model.PaymentRequest
import system_design.payment_gateway.model.PaymentResponse
import system_design.payment_gateway.PaymentStatus

interface PaymentService {
    fun initiatePayment(request: PaymentRequest): PaymentResponse
    fun validatePayment(request: PaymentRequest): Boolean
    fun processPayment(transactionId: String): PaymentStatus
    fun getTransactionStatus(transactionId: String): PaymentStatus
}