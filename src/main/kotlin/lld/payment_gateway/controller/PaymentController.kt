package lld.payment_gateway.controller

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import lld.payment_gateway.model.PaymentRequest
import lld.payment_gateway.service.PaymentService

class PaymentController(private val paymentService: PaymentService) {

    suspend fun initiatePayment(call: ApplicationCall) {
        val request = call.receive<PaymentRequest>()
        val response = paymentService.initiatePayment(request)
        call.respond(response)
    }

    suspend fun getTransactionStatus(call: ApplicationCall) {
        val transactionId = call.parameters["transactionId"] ?: ""
        val status = paymentService.getTransactionStatus(transactionId)
        call.respond(status)
    }
}