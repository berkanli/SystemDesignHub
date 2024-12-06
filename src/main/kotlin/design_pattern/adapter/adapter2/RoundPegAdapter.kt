package design_pattern.adapter.adapter2

import kotlin.math.sqrt

// Adapter to fit RoundPeg into SquareHole
class RoundPegAdapter(private val roundPeg: RoundPeg) {
    // This method calculates the equivalent square width for the round peg
    fun getSquareWidth(): Double {
        // Convert round peg's radius to a square peg's width using the diagonal formula
        return roundPeg.radius * Math.sqrt(2.0)
    }
}
