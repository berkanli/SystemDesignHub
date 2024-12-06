package design_pattern.adapter.adapter8

class EuropeanToUSAdapter(private val usPowerOutlet: USPowerOutlet) : EuropeanDevice() {
    fun adaptAndPowerOn() {
        val voltageFromUS = usPowerOutlet.providePower()  // Retrieve voltage from US outlet
        val adaptedVoltage = if (voltageFromUS == 120) 220 else voltageFromUS
        println("Adapting $voltageFromUS V to $adaptedVoltage V for European device")
        powerOn(adaptedVoltage)
    }
}