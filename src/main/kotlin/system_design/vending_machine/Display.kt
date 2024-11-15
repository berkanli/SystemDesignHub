package system_design.vending_machine

class Display {
    fun showMessage(message: String) {
        println(message)
    }

    fun showPrice(price: Double) {
        println("Price: $$price")
    }
}