package design_pattern.adapter.adapter8

fun main() {
    val usOutlet = StandardUSOutlet()
    println(usOutlet.providePower())

    val euOutlet = EuropeanDevice()
    val europeanAdapter = EuropeanAdapter(euOutlet)

    europeanAdapter.providePower()
}