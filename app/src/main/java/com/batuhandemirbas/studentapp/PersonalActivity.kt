package com.batuhandemirbas.studentapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.batuhandemirbas.studentapp.databinding.ActivityMainBinding
import com.batuhandemirbas.studentapp.databinding.ActivityPersonalBinding

class PersonalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        var view = binding.root

        Log.d("PersonalActivity", "onCreate Called")
        setContentView(view)

        val intent = intent
        val name = intent.getStringExtra("name")
        binding.textView.text = "Merhaba $name, lütfen hangi işlemi\nyapacağına karar ver."

        sharedPreferences = getSharedPreferences("package com.batuhandemirbas.studentapp", MODE_PRIVATE)
        binding.buttoneExit.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("PersonalActivity", "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("PersonalActivity", "onResume Called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        Log.d("PersonalActivity", "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PersonalActivity", "onStop Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("PersonalActivity", "onRestart Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PersonalActivity", "onDestroy Called")
    }
}