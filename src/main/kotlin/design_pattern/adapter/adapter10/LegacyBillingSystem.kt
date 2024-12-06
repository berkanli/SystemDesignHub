package design_pattern.adapter.adapter10

class LegacyBillingSystem: LegacyPaymentSystem {
    override fun processPaymentInCents(amountInCents: Double) {
        println("Processing payment of $${amountInCents / 100.0} through the legacy system.")
    }
}