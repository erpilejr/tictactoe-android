package dev.harness.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun AppRoot() {
    var game by remember { mutableStateOf(GameModel()) }
    GameScreen(game, onTap = { game = game.tap(it) }, onRestart = { game = GameModel() })
}

@Composable
fun GameScreen(game: GameModel, onTap: (Int) -> Unit, onRestart: () -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Text(text = game.turnText, modifier = Modifier.testTag("turn"))
        for (row in 0 until 3) {
            Row {
                for (col in 0 until 3) {
                    val n = row * 3 + col
                    Button(
                        onClick = { onTap(n) },
                        modifier = Modifier.size(96.dp).testTag("square-$n")
                    ) {
                        Text(text = game.squares[n])
                    }
                }
            }
        }
        Button(onClick = onRestart, modifier = Modifier.testTag("restart-button")) {
            Text(text = "Restart")
        }
    }
}
