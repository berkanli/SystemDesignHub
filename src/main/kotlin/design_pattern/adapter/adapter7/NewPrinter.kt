package design_pattern.adapter.adapter7

class NewPrinter {
    fun establishConnection(){
        println("Establishing connecting to new printer...")
    }

    fun transmitData(data: String){
        println("Transmitting data to new printer: $data")
    }
}