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
            "012",
            "345",
            "678",
            "036",
            "147",
            "258",
            "048",
            "246"
        ).map { line -> line.map { it - '0' } }
    }
}
