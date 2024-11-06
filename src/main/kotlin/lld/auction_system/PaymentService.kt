package lld.auction_system

class PaymentService {
    fun processPayment(buyer: User, seller: User, amount: Double): Boolean {
        if (buyer.balance < amount) {
            println("Payment failed: Insufficient balance.")
            return false
        }
        // Simulate a payment transfer
        buyer.balance -= amount
        seller.balance += amount
        println("Payment processed: ${buyer.username} paid $amount to ${seller.username}")
        return true
    }
}