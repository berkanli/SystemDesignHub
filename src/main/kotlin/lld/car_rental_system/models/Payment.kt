package lld.car_rental_system.models

data class Payment(
    val paymentId: String,
    val amount: Double,
    val date: String,
    val reservationId: String,
    var isPaid: Boolean
)