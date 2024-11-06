package lld.auction_system

import java.time.LocalDateTime

fun main() {
    val user1 = User("user1", "Alice", "alice@example.com", 1000.0)
    val user2 = User("user2", "Bob", "bob@example.com", 500.0)

    // User1 creates an auction
    val auction = Auction(
        auctionId = "auction1",
        seller = user1,
        itemDescription = "Vintage Watch",
        startPrice = 100.0,
        startTime = LocalDateTime.now(),
        endTime = LocalDateTime.now().plusDays(1)
    )
    user1.createAuction(auction)

    // User2 places a bid
    user2.placeBid(auction, 150.0)

    // Close auction and process payment
    auction.closeAuction()
    val winner = auction.highestBid?.user
    if (winner != null) {
        PaymentService().processPayment(winner, auction.seller, auction.highestBid!!.amount)
        NotificationService().notifyAuctionEnd(auction, winner)
    }
}
