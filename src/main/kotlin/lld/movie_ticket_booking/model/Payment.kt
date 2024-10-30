package lld.movie_ticket_booking.model

import lld.movie_ticket_booking.enums.PaymentStatus
import java.time.LocalDateTime

data class Payment(
    val paymentID: String,
    var status: PaymentStatus = PaymentStatus.PENDING,
    val amount: Double,
    val paymentDate: LocalDateTime = LocalDateTime.now()
)
