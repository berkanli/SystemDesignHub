package design_pattern.adapter.adapter8

class EuropeanAdapter(private val europeanDevice: EuropeanDevice): USPowerOutlet {
    override fun providePower(): Int {
        val adaptedVoltage = 220
        println("Adapting 120V to 220V for European device")
        europeanDevice.powerOn(adaptedVoltage)
        return adaptedVoltage
    }
}