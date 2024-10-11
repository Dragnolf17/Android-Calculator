package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalcButton
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.DarkBlue
import com.example.calculator.ui.theme.DirtyWhite

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("0") }
    var operand by remember { mutableStateOf(0.0) }
    var operator by remember { mutableStateOf("") }
    var userIsInTheMiddleOfTyping by remember { mutableStateOf(true) }

    fun setDisplay (value: Double){
        if (value % 1 == 0.0) {
            displayText = value.toInt().toString()
        } else{
            displayText = value.toString()
        }
    }

    fun getDisplay(): Double {
        return displayText.toDouble()
    }

    val onNumPressed: (String) -> Unit = { num ->
        if (userIsInTheMiddleOfTyping) {
            if (displayText == "0") {
                if (num == ".") {
                    displayText = "0."
                } else {
                    displayText = num
                }
            } else {
                if (!displayText.contains(".") || num != ".") {
                    displayText += num
                }
            }
        } else{
            displayText = num
        }

        userIsInTheMiddleOfTyping = true
    }

    val onOperatorPressed: (String) -> Unit = { op ->
        if(operator.isNotEmpty()) {
            when(operator) {
                "+" -> operand += getDisplay()
                "-" -> operand -= getDisplay()
                "*" -> operand *= getDisplay()
                "/" -> operand /= getDisplay()
                "=" -> operator = ""
            }
            setDisplay(operand)
        }

        operand = getDisplay()
        operator = op

        userIsInTheMiddleOfTyping = false
    }

    Column(modifier = modifier
        .fillMaxWidth()
        .background(DarkBlue)
        .padding(4.dp)) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Right,
            text = displayText,
            style = MaterialTheme.typography.displayLarge,
            color = DirtyWhite
        )

        Row(modifier = Modifier.weight(1f)) {
            CalcButton(modifier = Modifier.weight(1f), label = "7", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "8", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "9", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "+", isOperation = true, onClick = onOperatorPressed)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(modifier = Modifier.weight(1f), label = "4", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "5", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "6", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "-", isOperation = true, onClick = onOperatorPressed)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(modifier = Modifier.weight(1f), label = "1", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "2", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "3", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "*", isOperation = true, onClick = onOperatorPressed)
        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(modifier = Modifier.weight(1f), label = "0", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = ".", onClick = onNumPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "=", onClick = onOperatorPressed)
            CalcButton(modifier = Modifier.weight(1f), label = "/", isOperation = true, onClick = onOperatorPressed)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}