package sieteymedia

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.graphics.Color

@Composable
@Preview
fun App(game: SieteYMedia) {
    var valor =0.0
    var pantalla by remember { mutableStateOf("Siete Y Media") }
    var info by remember { mutableStateOf("") }
    var valores by remember { mutableStateOf("") }
    var gameOver by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(valores ,color=Color.Blue)
            Spacer(modifier = Modifier.height(34.dp))
            Text(info, color = if (info == "Has perdido") Color.Red else Color.Green)
            Spacer(modifier = Modifier.height(34.dp))
            Text(pantalla ,color=Color.Blue)
            Spacer(modifier = Modifier.height(68.dp))
            Row {
                Button(onClick =
                {
                    game.turnoBanca()
                    pantalla = game.mostrarCartas(game.cartasBanca)
                    valores = "Tu Valor: ${valor}     Valor Banca: ${game.valorCartas(game.cartasBanca)}"
                    if (game.valorCartas(game.cartasBanca) >= valor && game.valorCartas(game.cartasBanca) <= 7.5) {
                        info = "Has perdido"
                        gameOver = true
                    }
                    else{
                        info = "Has ganado"
                        gameOver = true
                    }
                }, enabled = !gameOver
                ) {
                    Text("Plantarse")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick =
                {
                    game.turnoJugador()
                    pantalla = game.mostrarCartas(game.cartasJugador)
                    valor = game.valorCartas(game.cartasJugador);
                    valores = "Tu Valor: ${valor}     Valor Banca: ${game.valorCartas(game.cartasBanca)}"
                    if (valor > 7.5) {
                        info = "Has perdido"
                        gameOver = true
                    }
                }, enabled = !gameOver
                )
                {
                    Text("Pedir carta")
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val game= SieteYMedia()
        App(game)
    }
}
