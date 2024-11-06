package lld.auction_system

class NotificationService {
    fun notifyBidUpdate(auction: Auction, bid: Bid) {
        println("Notification: New bid of ${bid.amount} placed by ${bid.user.username} on auction for ${auction.itemDescription}")
    }

    fun notifyAuctionEnd(auction: Auction, winner: User) {
        println("Notification: Auction ended for ${auction.itemDescription}. Winner is ${winner.username}.")
    }
}