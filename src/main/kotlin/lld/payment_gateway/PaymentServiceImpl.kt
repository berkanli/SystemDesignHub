package lld.payment_gateway



class PaymentServiceImpl(
    private val transactionRepository: TransactionRepository,
    private val paymentProcessor: PaymentProcessor
) : PaymentService {

    override fun initiatePayment(request: PaymentRequest): PaymentResponse {
        if (!validatePayment(request)) {
            return PaymentResponse(
                transactionId = "",
                status = PaymentStatus.FAILED,
                message = "Invalid payment request."
            )
        }

        val transaction = Transaction(
            amount = request.amount,
            currency = request.currency,
            sourceAccountId = request.sourceAccountId,
            destinationAccountId = request.destinationAccountId,
            status = PaymentStatus.INITIATED
        )

        val transactionId = transactionRepository.saveTransaction(transaction)
        return PaymentResponse(
            transactionId = transactionId,
            status = PaymentStatus.PENDING,
            message = "Payment initiated successfully."
        )
    }

    override fun validatePayment(request: PaymentRequest): Boolean {
        // Implement validation logic (e.g., check account balances, method validity)
        return true // Placeholder for validation result
    }

    override fun processPayment(transactionId: String): PaymentStatus {
        val transaction = transactionRepository.getTransaction(transactionId)
            ?: return PaymentStatus.FAILED

        val status = paymentProcessor.process(transaction) // Invoke third-party API or bank API
        transactionRepository.updateTransactionStatus(transactionId, status)
        return status
    }

    override fun getTransactionStatus(transactionId: String): PaymentStatus {
        return transactionRepository.getTransaction(transactionId)?.status ?: PaymentStatus.FAILED
    }
}
