package com.example.pawpal.ui

import android.content.Intent
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pawpal.R

class Onboard2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboard2)

        applyOptionalBackground()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<MaterialButton>(R.id.cta).setOnClickListener {
            startActivity(Intent(this, Onboard3::class.java))
        }
    }

    private fun applyOptionalBackground() {
        val root = findViewById<android.view.View>(R.id.main)
        val bgId = resources.getIdentifier("bg_app", "drawable", packageName)
        if (bgId != 0) root.setBackgroundResource(bgId)
    }
}
