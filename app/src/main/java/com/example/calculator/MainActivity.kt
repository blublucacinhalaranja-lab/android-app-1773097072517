```kotlin
package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.button0.setOnClickListener { appendToResult("0") }
        binding.button1.setOnClickListener { appendToResult("1") }
        binding.button2.setOnClickListener { appendToResult("2") }
        binding.button3.setOnClickListener { appendToResult("3") }
        binding.button4.setOnClickListener { appendToResult("4") }
        binding.button5.setOnClickListener { appendToResult("5") }
        binding.button6.setOnClickListener { appendToResult("6") }
        binding.button7.setOnClickListener { appendToResult("7") }
        binding.button8.setOnClickListener { appendToResult("8") }
        binding.button9.setOnClickListener { appendToResult("9") }
        binding.buttonDecimal.setOnClickListener { appendToResult(".") }

        binding.buttonAdd.setOnClickListener { performOperation("+") }
        binding.buttonSubtract.setOnClickListener { performOperation("-") }
        binding.buttonMultiply.setOnClickListener { performOperation("*") }
        binding.buttonDivide.setOnClickListener { performOperation("/") }
        binding.buttonPercent.setOnClickListener { performOperation("%") }
        binding.buttonPlusMinus.setOnClickListener { performPlusMinus() }

        binding.buttonClear.setOnClickListener { clearResult() }
        binding.buttonEquals.setOnClickListener { calculateResult() }
    }

    private fun appendToResult(value: String) {
        val currentText = binding.textViewResult.text.toString()
        if (currentText == "0" && value != ".") {
            binding.textViewResult.text = value
        } else {
            binding.textViewResult.text = currentText + value
        }
    }

    private fun performOperation(operation: String) {
        try {
            operand1 = binding.textViewResult.text.toString().toDouble()
            operator = operation
            binding.textViewResult.text = "0"
        } catch (e: NumberFormatException) {
            binding.textViewResult.text = "Error"
        }
    }

    private fun performPlusMinus() {
        try {
            val currentValue = binding.textViewResult.text.toString().toDouble()
            binding.textViewResult.text = DecimalFormat("#.##########").format(-currentValue)
        } catch (e: NumberFormatException) {
            binding.textViewResult.text = "Error"
        }
    }

    private fun clearResult() {
        binding.textViewResult.text = "0"
        operand1 = null
        operator = null
    }

    private fun calculateResult() {
        if (operand1 == null || operator == null) return

        try {
            val operand2 = binding.textViewResult.text.toString().toDouble()
            val result = when (operator) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> {
                    if (operand2 == 0.0) {
                        binding.textViewResult.text = "Error"
                        return
                    }
                    operand1!! / operand2
                }
                "%" -> operand1!! % operand2
                else -> return
            }

            binding.textViewResult.text = DecimalFormat("#.##########").format(result)
            operand1 = null
            operator = null

        } catch (e: NumberFormatException) {
            binding.textViewResult.text = "Error"
        }
    }
}
```