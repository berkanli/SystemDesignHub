package design_pattern.adapter.adapter2

// The SquareHole class, which accepts only SquarePeg objects
class SquareHole(val width: Double) {
    fun fits(peg: SquarePeg): Boolean {
        return peg.width <= this.width
    }
}