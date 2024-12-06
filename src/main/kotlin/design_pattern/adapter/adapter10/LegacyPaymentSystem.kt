package design_pattern.adapter.adapter10

interface LegacyPaymentSystem {
    fun processPaymentInCents(amountInCents: Double)
}