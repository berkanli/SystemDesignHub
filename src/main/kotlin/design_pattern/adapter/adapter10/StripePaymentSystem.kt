package design_pattern.adapter.adapter10

class StripePaymentSystem {
    fun processPaymentInDollars(amountInDollars: Double){
        println("Processing payment of $$amountInDollars through Stripe")
    }
}