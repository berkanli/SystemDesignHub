package design_pattern.adapter.adapter7

class OldPrinter: OldDevice {
    override fun connect() {
        println("Connecting to old printer...")
    }

    override fun sendData(data: String) {
        println("Sending data to old printer: $data")
    }
}