package com.batuhandemirbas.studentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.batuhandemirbas.studentapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Applied ViewBinding to bind components in UI part to program part
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        // Shows activity_main.xml on the screen.
        setContentView(view)

        // Log message for activity lifecycle
        Log.d("MainActivity", "onCreate Called")

    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume Called")
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