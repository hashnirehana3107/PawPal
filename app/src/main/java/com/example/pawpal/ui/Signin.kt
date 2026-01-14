package com.example.pawpal.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpal.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Signin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnSignIn = findViewById<MaterialButton>(R.id.btnSignIn)
        val tvSwitch = findViewById<TextView>(R.id.tvSwitchToSignUp)
        val btnGuest = findViewById<MaterialButton>(R.id.btnContinueAsGuest)

        val goHome = {
            startActivity(Intent(this, Home::class.java))
            overridePendingTransition(0,0)
            finish()
        }

        btnSignIn.setOnClickListener { goHome() }
        btnGuest.setOnClickListener { goHome() }
        tvSwitch.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
            overridePendingTransition(0,0)
            finish()
        }
    }
}
