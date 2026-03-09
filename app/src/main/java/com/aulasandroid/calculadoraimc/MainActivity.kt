package com.aulasandroid.calculadoraimc


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulasandroid.calculadoraimc.ui.theme.CalculadoraImcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraImcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraImcMainScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun CalculadoraImcMainScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        // --header
        Column(
            modifier = Modifier.fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BmiIcon(size = 80.dp, modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 32.dp)) {
            // -- form
            Card(modifier = Modifier
//            fillMaxWidth()
//                    .height(300.dp)
                    .size(250.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = CircleShape,
                border = BorderStroke(width = 4.dp, Color.Black)
            ) { }
            // -- result
        }
    }

}
@Composable
fun BmiIcon(modifier: Modifier = Modifier, size: Dp) {
    Image(
        contentDescription = "BMI",
        painter = painterResource(R.drawable.bmi),
        modifier = modifier.size(size),

    )
}

fun classificarImc(imc: Double): String {
    return when {
        imc >= 40 -> "Obesidade Grau III"
        imc >= 35 -> "Obesidade Grau II"
        imc >= 30 -> "Obesidade Grau I"
        imc >= 25 -> "Levemente acima do peso"
        imc >= 18.5 -> "Peso normal"
        else -> "Abaixo do peso"
    }
}

fun classificarCorImc(imc: Double): Color {
    return when {
        imc >= 40 -> Color.Red
        imc >= 35 -> Color.Red
        imc >= 30 -> Color(255, 152, 0, 255)
        imc >= 25 -> Color.Green
        imc >= 18.5 -> Color.Red
        else -> Color.Transparent
    }
}

