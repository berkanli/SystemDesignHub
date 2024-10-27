package lld.car_rental_system.repositories

import lld.car_rental_system.models.RentalReservation

class ReservationRepository {
    private val reservations = mutableListOf<RentalReservation>()

    fun addReservation(reservation: RentalReservation) {
        reservations.add(reservation)
    }

    fun findReservationById(reservationId: String): RentalReservation? {
        return reservations.find { it.reservationId == reservationId }
    }

    fun updateReservation(reservation: RentalReservation) {
        val index = reservations.indexOfFirst { it.reservationId == reservation.reservationId }
        if (index != -1) {
            reservations[index] = reservation
        }
    }

    val size: Int
        get() = reservations.size
}