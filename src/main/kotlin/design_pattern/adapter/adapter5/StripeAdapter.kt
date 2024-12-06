package design_pattern.adapter.adapter5

class StripeAdapter(private val stripe: Stripe): PaymentProcessor {
    override fun processPayment(amount: Double) {
        stripe.charge(amount) // Adapting the method
    }
}