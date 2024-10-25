package lld.parking_lot

object PaymentProcessor {
    fun calculateFee(ticket: Ticket): Double {
        val duration = (ticket.exitTime ?: System.currentTimeMillis()) - ticket.entryTime
        val ratePerHour  = when (ticket.vehicle.vehicleType){
            VehicleType.LARGE -> 3.5
            VehicleType.MEDIUM -> 2.0
            VehicleType.SMALL -> 1.5
        }
        return (duration / (1000.0 * 60 * 60)) * ratePerHour
    }
}