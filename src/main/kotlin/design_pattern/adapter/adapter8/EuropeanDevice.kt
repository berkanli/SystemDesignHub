package design_pattern.adapter.adapter8

open class EuropeanDevice {
    fun powerOn(voltage: Int){
        if(voltage == 220){
            println("European device powered on with 220V")
        } else {
            println("Warning: European device requires 220V, but received $voltage V")
        }
    }
}