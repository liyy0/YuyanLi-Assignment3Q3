package com.example.yuyanli_assignment3q3

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private lateinit var seekBarC: SeekBar
    private lateinit var seekBarF: SeekBar
    private lateinit var celsiusValue: TextView
    private lateinit var fahrenheitValue: TextView
    private lateinit var message: TextView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarC = findViewById(R.id.seekBarC)
        seekBarF = findViewById(R.id.seekBarF)
        celsiusValue = findViewById(R.id.CelsiusValue)
        fahrenheitValue = findViewById(R.id.FahrenheitValue)
        message = findViewById(R.id.Msg)

        seekBarC.max = 100
        seekBarC.min = 0
        seekBarF.max = 212
        seekBarF.min = 0
        seekBarF.progress = 32

        // https://www.youtube.com/watch?v=PhyITLW0q6A
        seekBarC.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val fahrenheit = CtoF(progress)
                    seekBarF.progress = fahrenheit
                    updateview(progress, fahrenheit)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarF.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val celsius = FtoC(progress)
                    seekBarC.progress = celsius
                    updateview(celsius, progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBarF.progress < 32) {
                    seekBarF.progress = 0
                    updateview(0, 32)
                    seekBarF.progress = 32
                }
            }
        })

    }

    // https://www.metric-conversions.org/temperature/celsius-to-fahrenheit.htm?arg=100

    private fun CtoF(celsius: Int): Int {
        return (celsius * 9 / 5) + 32
    }
    private fun FtoC(fahrenheit: Int): Int {
        return (fahrenheit - 32) * 5 / 9
    }

    private fun updateview(celsius: Int, fahrenheit: Int) {
        celsiusValue.text = "${celsius}ºC"
        fahrenheitValue.text = "${fahrenheit}ºF"
        if (celsius <= 20) {
            message.text = "I wish it were warmer."
        } else {
            message.text = "I wish it were colder."
        }


    }




}