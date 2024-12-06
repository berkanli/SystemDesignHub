package design_pattern.adapter.adapter13

fun main() {
    val airlineXAPI = AirlineXAPI()
    val hotelYAPI = HotelYAPI()
    val bookingAdapter = BookingAdapter(airlineXAPI, hotelYAPI)

    bookingAdapter.bookTravel("airlineXAPI", "13F ")
    bookingAdapter.bookTravel("hotelYAPI", "Room 237")
    bookingAdapter.bookTravel("bass", "Room 237")
}