package design_pattern.adapter.adapter2

// Testing the Adapter pattern
fun main() {
    val squareHole = SquareHole(5.0)
    val smallRoundPeg = RoundPeg(2.0)
    val largeRoundPeg = RoundPeg(5.0)

    val smallRoundPegAdapter = RoundPegAdapter(smallRoundPeg)
    val largeRoundPegAdapter = RoundPegAdapter(largeRoundPeg)

    // Using the adapter to convert RoundPeg to a square width and checking if it fits
    println("Small round peg fits in the square hole: ${squareHole.fits(SquarePeg(smallRoundPegAdapter.getSquareWidth()))}") // true
    println("Large round peg fits in the square hole: ${squareHole.fits(SquarePeg(largeRoundPegAdapter.getSquareWidth()))}") // false
}