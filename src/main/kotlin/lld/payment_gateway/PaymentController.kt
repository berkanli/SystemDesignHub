package lld.payment_gateway

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

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