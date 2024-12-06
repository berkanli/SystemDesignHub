package design_pattern.adapter.adapter5

fun main() {
    val paypal = PaypalProcessor()
    paypal.processPayment(100.0)

    val stripe = Stripe()
    val adapter = StripeAdapter(stripe)
    adapter.processPayment(200.0)
}