package design_pattern.adapter.adapter13

class BookingAdapter(
    private val airlineXAPI: AirlineXAPI,
    private val hotelYAPI: HotelYAPI
): TravelBooking {
    override fun bookTravel(serviceType: String, bookingDetails: String) {
        when (serviceType) {
            ::airlineXAPI.name -> {
                airlineXAPI.bookFlight(bookingDetails)
            }
            ::hotelYAPI.name -> {
                hotelYAPI.bookRoom(bookingDetails)
            }
            else -> {
                println("Service type not supported")
            }
        }
    }
}