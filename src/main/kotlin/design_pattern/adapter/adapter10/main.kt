package design_pattern.adapter.adapter10

fun main() {
    val legacy = LegacyBillingSystem()
    legacy.processPaymentInCents(240.0)

    val stripePaymentSystem = StripePaymentSystem()
    stripePaymentSystem.processPaymentInDollars(240.0)

    val adapter = StripeAdapter(stripePaymentSystem)
    adapter.processPaymentInCents(240.0)
}