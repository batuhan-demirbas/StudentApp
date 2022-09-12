package com.batuhandemirbas.studentapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.batuhandemirbas.studentapp.databinding.ActivityAverageBinding

class AverageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAverageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAverageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var vize: Int
        var final: Int
        var average: Double

        binding.buttonCalculate.setOnClickListener {
            vize = binding.editTextVize.text.toString().toInt()
            final = binding.editTextFinal.text.toString().toInt()
            average = vize * 0.4 + final * 0.6

            binding.textViewResult.apply {
                visibility = View.VISIBLE
                if (average >= 50 && final >= 50) {
                    text = "Sonuç: $average, geçtiniz :)"
                    setTextColor(Color.GREEN)
                } else {
                    text = "Sonuç: $average, kaldınız :("
                    setTextColor(Color.RED)
                }
            }
        }
    }
}