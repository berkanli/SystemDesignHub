package design_pattern.adapter.adapter5

class Stripe {
    fun charge(amount: Double){
        println("Charging \$${amount} through Stripe")
    }
}