package lld.auction_system

import java.time.LocalDateTime

enum class AuctionStatus {
    ACTIVE, CLOSED
}

class Auction(
    val auctionId: String,
    val seller: User,
    var itemDescription: String,
    var startPrice: Double,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
) {
    var highestBid: Bid? = null
    var status: AuctionStatus = AuctionStatus.ACTIVE
    private val bids: MutableList<Bid> = mutableListOf()

    fun placeBid(bid: Bid): Boolean {
        // Check auction status, bid validity, and update the highest bid
        if (status == AuctionStatus.CLOSED) {
            println("Auction is closed. Cannot place a bid.")
            return false
        }
        if (bid.amount <= (highestBid?.amount ?: startPrice)) {
            println("Bid is too low.")
            return false
        }
        // Place the bid
        bids.add(bid)
        highestBid = bid
        println("Bid placed by ${bid.user.username} with amount ${bid.amount}")
        return true
    }

    fun closeAuction(): User? {
        // Close the auction and determine the winner
        status = AuctionStatus.CLOSED
        println("Auction closed for item: $itemDescription")
        return highestBid?.user
    }
}