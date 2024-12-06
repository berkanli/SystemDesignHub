package design_pattern.adapter.adapter7

fun main() {
    val oldPrinter = OldPrinter()
    oldPrinter.connect()
    oldPrinter.sendData("Old Document")

    val newPrinter = NewPrinter()
    val adapter = NewPrinterAdapter(newPrinter)
    adapter.connect()
    adapter.sendData("New Document")
}