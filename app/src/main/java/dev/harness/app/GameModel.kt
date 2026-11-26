package dev.harness.app

data class GameModel(
    val squares: List<String> = List(SQUARE_COUNT) { "" },
    val xTurn: Boolean = true
) {
    private val winner: String?
        get() = LINES.firstNotNullOfOrNull { (a, b, c) ->
            squares[a].takeIf { it.isNotEmpty() && it == squares[b] && it == squares[c] }
        }

    val turnText: String
        get() = when {
            winner != null -> "$winner wins!"
            squares.all { it.isNotEmpty() } -> "It's a draw!"
            xTurn -> "X's turn"
            else -> "O's turn"
        }

    fun tap(index: Int): GameModel {
        if (winner != null || index !in squares.indices || squares[index].isNotEmpty()) return this
        val mark = if (xTurn) "X" else "O"
        return GameModel(squares.toMutableList().also { it[index] = mark }, !xTurn)
    }

    companion object {
        const val SQUARE_COUNT = 9
        private val LINES = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )
    }
}
