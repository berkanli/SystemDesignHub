package design_pattern.adapter.adapter13

// Third-party hotel API (HotelY)
class HotelYAPI {
    fun bookRoom(hotelDetails: String){
        println("Booking room with HotelY: $hotelDetails")
    }
}