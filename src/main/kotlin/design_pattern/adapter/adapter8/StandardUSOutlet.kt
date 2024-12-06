package design_pattern.adapter.adapter8

class StandardUSOutlet: USPowerOutlet {
    override fun providePower(): Int {
        println("Providing 120V from US outlet")
        return 120
    }
}