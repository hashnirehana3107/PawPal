package com.example.pawpal.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpal.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnSignUp = findViewById<MaterialButton>(R.id.btnSignUp)
        val tvSwitch = findViewById<TextView>(R.id.tvSwitchToSignIn)

        btnSignUp.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            overridePendingTransition(0,0)
            finish()
        }
        tvSwitch.setOnClickListener {
            startActivity(Intent(this, Signin::class.java))
            overridePendingTransition(0,0)
            finish()
        }
    }
}
