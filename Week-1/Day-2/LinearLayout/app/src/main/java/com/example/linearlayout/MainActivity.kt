package com.example.linearlayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var userName: TextView
    private lateinit var userInput: EditText
    private lateinit var finishApp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        getUserData()
    }


    private fun initComponents() {
        userName = findViewById(R.id.tvUserName)
        userInput = findViewById(R.id.etUserInput)
        finishApp = findViewById(R.id.btnFinish)
    }

    private fun getUserData() {
        finishApp.setOnClickListener {
            val data = userInput.text.toString()
            userName.text = data
        }

    }
}