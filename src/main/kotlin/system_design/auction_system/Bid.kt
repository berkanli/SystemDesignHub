package system_design.auction_system

import java.time.LocalDateTime

class Bid(
    val bidId: String,
    val user: User,
    val auction: Auction,
    val amount: Double,
    val bidTime: LocalDateTime
)