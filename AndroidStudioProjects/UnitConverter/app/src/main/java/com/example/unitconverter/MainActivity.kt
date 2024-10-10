package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    This is for the Unit Converter
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){


    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnity by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableStateOf(1.0) }
    val oConversionFactor by remember { mutableStateOf(1.0) }


    fun unitConverter2(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.absoluteValue * 100.0 / oConversionFactor.absoluteValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit_Converter",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = inputValue, onValueChange = { inputValue = it },
            label = { Text("Enter Value") })
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box{
                Button(onClick = { iExpand = true }) {
                    Text("Select")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                    DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false }) {

                        
                        DropdownMenuItem(text = { Text("Centimeter") },
                            onClick = {
                                iExpand = false
                                inputUnit = "Centimeter"
                                conversionFactor = 0.01
                                unitConverter2()
                            }
                        )
                        DropdownMenuItem(text = { Text("Meter") },
                            onClick = {
                                iExpand = false
                                inputUnit = "Meter"
                                conversionFactor = 1.0
                                unitConverter2()
                            }
                        )

                        DropdownMenuItem(text = { Text("Feet") },
                            onClick = {
                                iExpand = false
                                inputUnit = "Feet"
                                conversionFactor = 0.3048
                                unitConverter2()
                            }
                        )
                        DropdownMenuItem(text = { Text("Millimeter") },
                            onClick = {
                                iExpand = false
                                inputUnit = "Millimeter"
                                conversionFactor = 0.001
                                unitConverter2()
                            }
                        )
                    }

            }
            Spacer(modifier = Modifier.width(20.dp))
            Box {
                Button(onClick = { oExpand = true }) {
                    Text("Select")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                    DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeter") },
                            onClick = {
                                oExpand = false
                                outputUnity = "Centimeter"
                                conversionFactor = 0.01
                                unitConverter2()
                            }
                        )
                        DropdownMenuItem(text = { Text("Meter") },
                            onClick = {
                                oExpand = false
                                outputUnity = "Meter"
                                conversionFactor = 1.0
                                unitConverter2()
                            }
                        )
                        DropdownMenuItem(text = { Text("feet") },
                            onClick = {
                                oExpand = false
                                outputUnity = "feet"
                                conversionFactor = 0.3048
                                unitConverter2()
                            }
                        )
                        DropdownMenuItem(text = {Text( "Millimeters") },
                            onClick = {
                                oExpand = false
                                outputUnity = "Millimeters"
                                conversionFactor = 0.001
                                unitConverter2()
                            }
                        )
                    }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Result : $outputValue $outputUnity",
            style = MaterialTheme.typography.headlineLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
