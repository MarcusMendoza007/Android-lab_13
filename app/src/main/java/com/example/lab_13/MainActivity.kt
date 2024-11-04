package com.example.lab_13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedScreen()
        }
    }
}

@Composable
fun AnimatedScreen() {
    var isDarkMode by remember { mutableStateOf(false) }
    var boxSize by remember { mutableStateOf(100.dp) }
    var boxColor by remember { mutableStateOf(Color.Blue) }
    var buttonVisible by remember { mutableStateOf(true) }

    // Estilo para la caja
    val boxModifier = Modifier
        .size(boxSize)
        .background(boxColor)
        .clickable {
            // Cambia el tamaño y color de la caja al hacer clic
            boxSize = if (boxSize.value.roundToInt() == 100) 200.dp else 100.dp
            boxColor = if (boxColor == Color.Blue) Color.Red else Color.Blue
        }

    // Contenedor principal con fondo que cambia según el modo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkMode) Color.Black else Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Caja animada
        Box(modifier = boxModifier)

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para alternar entre modo claro y oscuro
        Button(
            onClick = { isDarkMode = !isDarkMode },
            colors = ButtonDefaults.buttonColors(containerColor = if (isDarkMode) Color.Gray else Color.LightGray)
        ) {
            Text("Cambiar a modo ${if (isDarkMode) "claro" else "oscuro"}", color = if (isDarkMode) Color.White else Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón que se desplaza y desaparece
        AnimatedVisibility(visible = buttonVisible) {
            Button(
                onClick = {
                    buttonVisible = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Desaparecer", color = Color.White)
            }
        }

        // Botón para mostrar el botón que desaparece
        Button(
            onClick = { buttonVisible = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text("Mostrar botón", color = Color.White)
        }
    }

    // Aplicar el modo oscuro o claro
    if (isDarkMode) {
        MaterialTheme(colorScheme = darkColorScheme()) {
            Text(
                "Modo Oscuro",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
        }
    } else {
        MaterialTheme(colorScheme = lightColorScheme()) {
            Text(
                "Modo Claro",
                color = Color.Black,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedScreen() {
    AnimatedScreen()
}