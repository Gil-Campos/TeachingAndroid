package com.example.userinputdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userinputdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MainActivity_LifeCycle", "onCreate called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button click listener to handle the data submission
        binding.btnSubmit.setOnClickListener {
            performIntent()
        }
    }

    private fun performIntent() {
        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString()

        // Check if input fields are empty
        if (name.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("name", name)
                putExtra("age", age)
            }

            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity_LifeCycle", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity_LifeCycle", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity_LifeCycle", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity_LifeCycle", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity_LifeCycle", "onDestroy called")
    }
}