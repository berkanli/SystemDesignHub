package design_pattern.adapter.adapter10

// Adapter class to convert between the legacy and modern payment systems
class StripeAdapter(private val stripePayment: StripePaymentSystem): LegacyPaymentSystem {
    override fun processPaymentInCents(amountInCents: Double) {
        val amountInDollars = amountInCents / 100.0
        stripePayment.processPaymentInDollars(amountInDollars)
    }

}