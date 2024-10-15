package com.example.userinputdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userinputdemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayUserData()
    }

    private fun displayUserData() {
        // Retrieve data from Intent
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")

        // Handle empty fields and display user input
        binding.tvName.text = if (name.isNullOrEmpty()) "Name: Nameless" else "Name: $name"
        binding.tvAge.text = if (age.isNullOrEmpty()) "Age: Invalid Age" else "Age: $age"

        // Button to go back to MainActivity
        binding.btnBack.setOnClickListener {
            finish() // Ends current activity and goes back to MainActivity
        }
    }
}