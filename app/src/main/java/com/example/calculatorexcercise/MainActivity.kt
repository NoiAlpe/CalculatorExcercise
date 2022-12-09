package com.example.calculatorexcercise

import android.app.admin.FactoryResetProtectionPolicy
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculatorexcercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var answer = 0.00
    var value1 = 0.00
    var op = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun ButtonClick (view : View) {

        val number = binding.txtResult

        if (view is Button) {
            when (view.text) {
                "0" -> {if (!number.text.isEmpty()) number.append("0")}
                "+" -> {
                        op = "+"
                        if (!number.text.isEmpty() && value1.equals(0.00)) {
                            value1 = number.text.toString().toDouble()
                            number.text = ""
                            operatorDisable(op)
                        } else if (!value1.equals(0.00)) {
                            answer = operation(op, value1, number.text.toString().toDouble())
                            number.text = answer.toString()
                            operatorDisable(reset = true)
                        }
                    }
                "-" -> {
                    if (number.text.isEmpty() || !answer.equals(0.00)) {
                        if (value1.equals(0.00) || !answer.equals(0.00)) resetValues(true)
                        number.append("-")
                    }

                    else {
                        op = "-"
                        Log.i("MAIN, SUBTRACT", "V1: $value1")
                        if (!number.text.isEmpty() && value1.equals(0.00)) {
                            value1 = number.text.toString().toDouble()
                            number.text = ""
                            operatorDisable(op)
                        } else if (!value1.equals(0.00)) {
                            Log.i("MAIN, SUBTRACT", "Inside else if - V1: $value1")
                            answer = operation(op, value1, number.text.toString().toDouble())
                            number.text = answer.toString()
                            operatorDisable(reset = true)
                        }
                    }
                }
                "x" -> {
                    op = "x"
                    if (!number.text.isEmpty() && value1.equals(0.00)) {
                        value1 = number.text.toString().toDouble()
                        number.text = ""
                        operatorDisable(op)
                    } else if (!value1.equals(0.00)) {
                        answer = operation(op, value1, number.text.toString().toDouble())
                        number.text = answer.toString()
                        operatorDisable(reset = true)
                    }
                }
                "÷" -> {
                    op = "÷"
                    if (!number.text.isEmpty() && value1.equals(0.00)) {
                        value1 = number.text.toString().toDouble()
                        number.text = ""
                        operatorDisable(op)
                    } else if (!value1.equals(0.00)) {
                        answer = operation(op, value1, number.text.toString().toDouble())
                        number.text = answer.toString()
                        operatorDisable(reset = true)
                    }
                }
                "=" -> {
                    if ((!value1.equals(0.00) && number.text.isNotEmpty()) || op != "") {
                        var value2 = number.text.toString().toDouble()
                        answer = operation(op, value1, value2)
                        number.text = answer.toString()
                        operatorDisable(reset = true)
                    }
                }
                "." -> {
                    if (!number.text.contains(".")) number.append(".")
                }
                "Clear" -> {
                    resetValues(true)
                    operatorDisable(reset = true)
                }
                else -> {
                    if (!answer.equals(0.00)) {
                        resetValues(true)
                        operatorDisable(reset = true)
                    }
                    number.append(view.text)
                }
            }
        }
    }

    fun operation(operator : String, value1 : Double, value2 : Double) : Double {
        return when (operator) {
            "+" -> value1 + value2
            "-" -> value1 - value2
            "÷" -> value1 / value2
            "x" -> value1 * value2
            else -> 0.0
        }
    }

    fun resetValues (txtReset : Boolean = false){
        answer = 0.00
        value1 = 0.00

        if (txtReset) binding.txtResult.text = ""

    }

    fun operatorDisable(operator: String = "", reset : Boolean = false) {
        if (!reset) {
            when (operator) {
                "+" -> {
                    binding.btnDivide.isEnabled = false
                    binding.btnDivide.isClickable = false

                    binding.btnMultiply.isEnabled = false
                    binding.btnMultiply.isClickable = false

                }
                "-" -> {
                    binding.btnDivide.isEnabled = false
                    binding.btnDivide.isClickable = false

                    binding.btnMultiply.isEnabled = false
                    binding.btnMultiply.isClickable = false

                    binding.btnAdd.isClickable = false
                    binding.btnAdd.isEnabled = false
                }
                "x" -> {
                    binding.btnDivide.isEnabled = false
                    binding.btnDivide.isClickable = false

                    binding.btnAdd.isEnabled = false
                    binding.btnAdd.isClickable = false

                }
                "÷" -> {
                    binding.btnMultiply.isEnabled = false
                    binding.btnMultiply.isClickable = false

                    binding.btnAdd.isEnabled = false
                    binding.btnAdd.isClickable = false

                }
            }
        } else {

            binding.btnMultiply.isEnabled = true
            binding.btnMultiply.isClickable = true

            binding.btnAdd.isEnabled = true
            binding.btnAdd.isClickable = true

            binding.btnMinus.isClickable = true
            binding.btnMinus.isEnabled = true

            binding.btnDivide.isClickable = true
            binding.btnDivide.isEnabled = true

            op = ""
        }
    }
}





