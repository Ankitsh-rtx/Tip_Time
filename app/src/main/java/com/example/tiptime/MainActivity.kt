package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.resultTextView.text = getString(R.string.tip_amount, "0.00")
        binding.calculateBtn.setOnClickListener { calculateTip() }
        binding.costEditTextLayout.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun calculateTip() {
        var cost = binding.costEditText.text.toString()
        if (cost == "") {
            cost = "0.00"
        }
        val costInput = cost.toDouble()
        val input = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radio_btn_20 -> 0.20
            R.id.radio_btn_18 -> 0.18
            else -> 0.15
        }
        var tip = (costInput * input)
        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }
        val tipFormat = NumberFormat.getNumberInstance().format(tip)
        binding.resultTextView.text = getString(R.string.tip_amount, tipFormat)

    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}