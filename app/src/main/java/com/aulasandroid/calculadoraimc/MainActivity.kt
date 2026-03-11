package com.aulasandroid.calculadoraimc


import android.R.attr.onClick
import android.R.attr.text
import android.R.attr.y
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aulasandroid.calculadoraimc.ui.theme.CalculadoraImcTheme
import java.util.Locale
import kotlin.math.round
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
    var espacamentoPadrao: Dp = 8.dp
    var espacamentoMaior: Dp = 32.dp
    Column(modifier = modifier.fillMaxSize()) {
        // --header
        Column(
            modifier = Modifier.fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            BmiIcon(size = 60.dp, modifier = Modifier.padding(vertical = 16.dp))
            Spacer(modifier = Modifier.height(espacamentoPadrao))
            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var valorAltura by remember {
                mutableStateOf("")
            }
            var valorPeso by remember {
                mutableStateOf("")
            }
            var resultClassificacao by remember {
                mutableStateOf("")
            }
            var resultImc by remember {
                mutableStateOf("")
            }
            var resultColor by remember {
                mutableStateOf(Color(255,255,255))
            }
            // -- form
            Card(modifier = Modifier
//            fillMaxWidth()
                    .height(325.dp)
                    .width(450.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),
//                shape = CircleShape,
                border = BorderStroke(width = 2.dp, Color(222, 218, 218, 255))
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = espacamentoMaior, vertical = espacamentoPadrao),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {


                    Text(
                        text = "Seus Dados",
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.cor_app),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )




                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = valorAltura,
                        onValueChange = {
                            valorAltura = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(text = "Altura")
                        },
                        label = {
                            Text(text = "Altura (cm ou m)")
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorResource(R.color.cor_app),
                            unfocusedBorderColor = colorResource(R.color.cor_app)
                        ),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    valorAltura = ""
                                }

                            )
                            {
                                Icon(
                                    contentDescription = "Sair",
                                    painter = painterResource(R.drawable.outline_cancel_24)
                                )
                            }

                        },

                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = valorPeso,
                        onValueChange = {
                            valorPeso = it
                        },

                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = {
                            Text(text = "Peso")
                        },
                        label = {
                            Text(text = "Peso (Kg)")
                        },

                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = colorResource(R.color.cor_app),
                            unfocusedBorderColor = colorResource(R.color.cor_app)
                        ) ,
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    valorPeso = ""
                                }

                            )
                            {
                               Icon(
                                   contentDescription = "Sair",
                                   painter = painterResource(R.drawable.outline_cancel_24)
                               )
                            }

                        },
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        onClick = {
                            val altura = valorAltura.toDoubleOrNull()
                            val peso = valorPeso.toDoubleOrNull()

                            if (altura != null && peso != null) {
                                val imc = calcularImc(
                                    altura = valorAltura.toDouble(),
                                    peso = valorPeso.toDouble()
                                )
                                resultImc = String.format(Locale.US, "%.2f", imc)
                                resultClassificacao = classificarImc(imc)
                                resultColor = classificarCorImc(imc)
                            } else {
                                resultImc = "0.0"
                                resultClassificacao = "Erro !"
                            }




                        },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                         colorResource(R.color.cor_app)
                        )
                    ) {
                        Text(
                            text = "CALCULAR",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }


                }
            }
            Spacer(modifier= Modifier.height(espacamentoMaior))
            // -- result
            Card(modifier = Modifier
//            fillMaxWidth()
                .height(100.dp)
                .width(450.dp)
                .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = resultColor
                ),
                elevation = CardDefaults.cardElevation(4.dp),
//                shape = CircleShape,
                border = BorderStroke(width = 2.dp, Color(222, 218, 218, 255))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = resultImc,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(255,255,255)
                    )
                    Text(
                        text = resultClassificacao,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(255,255,255)
                    )
                }
            }
        }
    }

}
@Composable
fun BmiIcon(modifier: Modifier = Modifier, size: Dp) {
    Image(
        contentDescription = "BMI",
        painter = painterResource(R.drawable.bmi),
        modifier = Modifier.size(size),

    )
}

fun calcularImc(altura: Double, peso: Double): Double {
    var alturaMetros: Double
    var result: Double
    if (altura > 3) {
        alturaMetros = altura / 100
        result = peso / (alturaMetros * alturaMetros)

    } else {
        result = peso / (altura * altura)
    }
    return result
}
fun classificarImc(imc: Double): String {
    return when {
        imc >= 40 -> "Obesidade Grau III"
        imc >= 35 -> "Obesidade Grau II"
        imc >= 30 -> "Obesidade Grau I"
        imc >= 25 -> "Levemente acima do peso"
        imc >= 18.5 -> "Peso normal"
        imc > 0 -> "Abaixo do peso"
        else -> "Erro"
    }
}

fun classificarCorImc(imc: Double): Color {
    return when {
        imc >= 40 -> Color.Red
        imc >= 35 -> Color.Red
        imc >= 30 -> Color.Red
        imc >= 25 -> Color(255, 152, 0, 255)
        imc >= 18.5 -> Color(68,169,41,234)
        else -> Color.Red
    }
}

