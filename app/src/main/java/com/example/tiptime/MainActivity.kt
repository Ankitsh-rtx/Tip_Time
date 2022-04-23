package com.example.tiptime

import android.os.Bundle
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
}