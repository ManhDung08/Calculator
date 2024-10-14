package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var state = 1
    var op1: Int = 0  //toan hang 1
    var op2: Int = 0  //toan hang 2
    var operator: Int = 0   // toan tu (x, /, +, -)
    var hasCalculated: Boolean = false // trang thai tinh toan
    lateinit var resultText: TextView
    lateinit var operationText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)
        operationText = findViewById(R.id.operationText)

        findViewById<Button>(R.id.BS_btn).setOnClickListener(this)
        findViewById<Button>(R.id.CE_btn).setOnClickListener(this)
        findViewById<Button>(R.id.C_btn).setOnClickListener(this)
        findViewById<Button>(R.id.multiply_btn).setOnClickListener(this)
        findViewById<Button>(R.id.divide_btn).setOnClickListener(this)
        findViewById<Button>(R.id.minus_btn).setOnClickListener(this)
        findViewById<Button>(R.id.plus_btn).setOnClickListener(this)
        findViewById<Button>(R.id.equal_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num0_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num1_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num2_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num3_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num4_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num5_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num6_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num7_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num8_btn).setOnClickListener(this)
        findViewById<Button>(R.id.num9_btn).setOnClickListener(this)
        findViewById<Button>(R.id.point_btn).setOnClickListener(this)
        findViewById<Button>(R.id.signChange_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.num0_btn -> addDigit(0)
            R.id.num1_btn -> addDigit(1)
            R.id.num2_btn -> addDigit(2)
            R.id.num3_btn -> addDigit(3)
            R.id.num4_btn -> addDigit(4)
            R.id.num5_btn -> addDigit(5)
            R.id.num6_btn -> addDigit(6)
            R.id.num7_btn -> addDigit(7)
            R.id.num8_btn -> addDigit(8)
            R.id.num9_btn -> addDigit(9)
            R.id.signChange_btn -> changeSign()
            R.id.point_btn -> addPoint()
            R.id.multiply_btn -> {operator = 1; updateOperationText("x"); state = 2; hasCalculated = false}
            R.id.divide_btn -> {operator = 2; updateOperationText("/"); state = 2; hasCalculated = false}
            R.id.plus_btn -> {operator = 3; updateOperationText("+"); state = 2; hasCalculated = false}
            R.id.minus_btn -> {operator = 4; updateOperationText("-"); state = 2; hasCalculated = false}
            R.id.equal_btn -> calculateResult()
            R.id.C_btn -> clearAll()
            R.id.CE_btn -> clearEntry()
            R.id.BS_btn -> backspace()
        }
    }

    fun addDigit(c: Int) {
        if (hasCalculated) {
            op1 = c
            op2 = 0
            resultText.text = "$op1"
            operationText.text = "$op1"
            state = 1
            hasCalculated = false
            return
        }

        if (state == 1) {
            op1 = op1 * 10 + c
            resultText.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            resultText.text = "$op2"
        }
        updateOperationText()
    }

    fun changeSign() {
        if (state == 1) {
            op1 = op1 * -1
            resultText.text = "$op1"
        } else {
            op2 = op2 * -1
            resultText.text = "$op2"
        }
        updateOperationText()
    }

    fun addPoint() {

    }

    fun calculateResult() {
        var result = 0
        when (operator) {
            1 -> result = op1 * op2
            2 -> result = if (op2 != 0) op1 / op2 else 0
            3 -> result = op1 + op2
            4 -> result = op1 - op2
        }
        resultText.text = "$result"
        operationText.text = "$op1${operatorToString(operator)}$op2="
        op1 = result
        op2 = 0
        state = 1
        hasCalculated = true
    }

    fun clearAll() {
        op1 = 0
        op2 = 0
        operator = 0
        state = 1
        resultText.text = "0"
        operationText.text = ""
        hasCalculated = false
    }

    fun clearEntry() {
        if (state == 1) {
            op1 = 0
            resultText.text = "0"
        } else {
            op2 = 0
            resultText.text = "0"
        }
        updateOperationText()
    }

    fun backspace() {
        if (state == 1) {
            op1 /= 10
            resultText.text = "$op1"
        } else {
            op2 /= 10
            resultText.text = "$op2"
        }
        updateOperationText()
    }

    fun updateOperationText(operatorSymbol: String? = null) {
        if(state == 1){
            val opSymbol = operatorSymbol ?: ""
            operationText.text = "$op1$opSymbol"
        }
        else{
            operationText.text = "$op1${operatorToString(operator)}$op2"
        }
    }
    fun operatorToString(operator: Int): String {
        return when (operator) {
            1 -> "x"
            2 -> "/"
            3 -> "+"
            4 -> "-"
            else -> ""
        }
    }
}