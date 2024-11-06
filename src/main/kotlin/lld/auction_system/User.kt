package lld.auction_system

import java.time.LocalDateTime

class User(
    val userId: String,
    var username: String,
    var email: String,
    var balance: Double
) {
    // List to hold auctions created by the user
    private val auctionsCreated: MutableList<Auction> = mutableListOf()

    fun createAuction(auction: Auction): Boolean {
        // Add the auction to the list of created auctions
        auctionsCreated.add(auction)
        println("Auction created by $username: ${auction.itemDescription}")
        return true
    }

    fun placeBid(auction: Auction, bidAmount: Double): Boolean {
        // Ensure the user has enough balance to place a bid
        if (balance < bidAmount) {
            println("Insufficient balance for bid.")
            return false
        }
        // Create a bid and place it on the auction
        val bid = Bid(bidId = "bid-${System.currentTimeMillis()}", user = this, auction = auction, amount = bidAmount, bidTime = LocalDateTime.now())
        return auction.placeBid(bid)
    }
}

