package design_pattern.adapter.adapter7

class NewPrinterAdapter(private val newPrinter: NewPrinter): OldDevice {
    override fun connect() {
        newPrinter.establishConnection() // Adapting the method
    }

    override fun sendData(data: String) {
        newPrinter.transmitData(data) // Adapting the method
    }
}