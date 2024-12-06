package design_pattern.adapter.adapter13

// Third-party airline API (AirlineX)
class AirlineXAPI {
    fun bookFlight(flightDetails: String) {
        println("Booking flight with AirlineX: $flightDetails")
    }
}