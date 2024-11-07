package lld.payment_gateway

class PaymentController(private val paymentService: PaymentService) {

    fun initiatePayment(call: ApplicationCall) {
        val request = call.receive<PaymentRequest>()
        val response = paymentService.initiatePayment(request)
        call.respond(response)
    }

    fun getTransactionStatus(call: ApplicationCall) {
        val transactionId = call.parameters["transactionId"] ?: ""
        val status = paymentService.getTransactionStatus(transactionId)
        call.respond(status)
    }
}