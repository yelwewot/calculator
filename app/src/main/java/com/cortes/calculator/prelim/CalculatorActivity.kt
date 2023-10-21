package com.cortes.calculator.prelim

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class CalculatorActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        resultText = findViewById(R.id.resultText)

        // Set the onClickListeners for the number buttons.
        val numberButtons = arrayOf(
            findViewById(R.id.buone),
            findViewById(R.id.butwo),
            findViewById(R.id.buthree),
            findViewById(R.id.bufour),
            findViewById(R.id.bufive),
            findViewById(R.id.busix),
            findViewById(R.id.buseven),
            findViewById(R.id.bueight),
            findViewById(R.id.bunine),
            findViewById<Button>(R.id.buzero)
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }

        // Set the onClickListeners for the operation buttons.
        val operationButtons = arrayOf(
            findViewById(R.id.buaddition),
            findViewById(R.id.busubtraction),
            findViewById(R.id.bumultiplication),
            findViewById(R.id.budivide),
            findViewById(R.id.buconversion),
            findViewById(R.id.buplusminus),
            findViewById<Button>(R.id.buequals)
        )

        for (button in operationButtons) {
            button.setOnClickListener {
                handleOperation(button.text.toString())
            }
        }

        // Set the onClickListeners for the clear and decimal buttons.
        findViewById<Button>(R.id.buAC).setOnClickListener {
            clear()
        }

        findViewById<Button>(R.id.budecimal).setOnClickListener {
            appendDecimal()
        }
    }

    private fun appendNumber(number: String) {
        if (operation.isEmpty()) {
            firstNumber = firstNumber * 10 + number.toDouble()
        } else {
            secondNumber = secondNumber * 10 + number.toDouble()
        }

        resultText.text = if (operation.isNotEmpty()) {
            "$firstNumber $operation $secondNumber"
        } else {
            "$firstNumber"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleOperation(operation: String) {
        if (this.operation.isNotEmpty()) {
            performCalculation()
        }

        this.operation = operation
        secondNumber = 0.0

        resultText.text = "$firstNumber $operation"
    }

    private fun performCalculation() {
        when (operation) {
            "+" -> firstNumber += secondNumber
            "-" -> firstNumber -= secondNumber
            "*" -> firstNumber *= secondNumber
            "/" -> firstNumber /= secondNumber
            "%" -> firstNumber /= 100
            "+/-" -> if(firstNumber>0) {
                firstNumber= -firstNumber
            }else return convert()
        }

        secondNumber = 0.0
        operation = ""

        resultText.text = firstNumber.toString()
    }

    private fun clear() {
        firstNumber = 0.0
        secondNumber = 0.0
        operation = ""

        resultText.text = "0"
    }
    private fun convert(){
        firstNumber *= -1;
    }


    @SuppressLint("SetTextI18n")
    private fun appendDecimal() {

        resultText.text = resultText.text.toString() + "."

        if (resultText.text.toString().contains(".")) {
            return
        }
    }
}
