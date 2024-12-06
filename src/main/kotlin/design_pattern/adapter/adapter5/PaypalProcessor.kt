package design_pattern.adapter.adapter5

class PaypalProcessor: PaymentProcessor {
    override fun processPayment(amount: Double) {
        println("Processing payment of \$${amount} through Paypal")
    }
}