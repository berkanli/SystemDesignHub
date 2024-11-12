package lld.payment_gateway

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import lld.payment_gateway.model.PaymentRequest
import lld.payment_gateway.repository.InMemoryTransactionRepository
import lld.payment_gateway.service.PaymentService
import lld.payment_gateway.service.PaymentServiceImpl

fun main() {
    // Initialize paymentService
    val paymentService: PaymentService = PaymentServiceImpl(
        transactionRepository = InMemoryTransactionRepository(),
        paymentProcessor = MockPaymentProcessor()
    )

    embeddedServer(Netty, port = 8080) {
        configureRouting(paymentService)
    }.start(wait = true)
}

// Define routes in a separate function, passing paymentService as a parameter
fun Application.configureRouting(paymentService: PaymentService) {
    routing {
        post("/initiate-payment") {
            val request = call.receive<PaymentRequest>()
            val response = paymentService.initiatePayment(request)
            call.respond(response)
        }

        get("/transaction-status/{transactionId}") {
            val transactionId = call.parameters["transactionId"] ?: ""
            val status = paymentService.getTransactionStatus(transactionId)
            call.respond(status)
        }
    }
}