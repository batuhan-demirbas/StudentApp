package com.batuhandemirbas.studentapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.batuhandemirbas.studentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    var getName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Applied ViewBinding to bind components in UI part to program part
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        // Shows activity_main.xml on the screen.
        setContentView(view)

        // Returns log and toast message for activity lifecycle
        Log.d("MainActivity", "onCreate Called")
        Toast.makeText(this, "Uygulama başlatıldı", Toast.LENGTH_SHORT).show()

        // Intent process is done for transition between activities and data transfer
        val intent = Intent(this, PersonalActivity::class.java)

        // asdsad
        sharedPreferences = this.getSharedPreferences("package com.batuhandemirbas.studentapp", MODE_PRIVATE)
        getName = sharedPreferences.getString("name", "")
        if (getName != "") {
            intent.putExtra("name", getName)
            startActivity(intent)
            finish()
        }


        // Actions to be applied when the login button is pressed
        val button = binding.button
        button.setOnClickListener {
            val name = binding.editText.text.toString()
            if (name == "") {
                Toast.makeText(this, "Lütfen isminizi giriniz.", Toast.LENGTH_SHORT).show()
            } else {
                intent.putExtra("name", name)
                sharedPreferences.edit().putString("name",name).apply()
                startActivity(intent)
                finish()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume Called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy Called")
    }
}