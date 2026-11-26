package dev.harness.app

data class GameModel(
    val squares: List<String> = List(SQUARE_COUNT) { "" },
    val xTurn: Boolean = true
) {
    val turnText: String get() = if (xTurn) "X's turn" else "O's turn"

    fun tap(index: Int): GameModel {
        if (index !in squares.indices || squares[index].isNotEmpty()) return this
        val mark = if (xTurn) "X" else "O"
        return GameModel(squares.toMutableList().also { it[index] = mark }, !xTurn)
    }

    companion object {
        const val SQUARE_COUNT = 9
    }
}
