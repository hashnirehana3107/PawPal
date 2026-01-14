package com.example.pawpal.ui


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpal.R
import com.google.android.material.button.MaterialButton
import android.widget.TextView

class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Beautiful “MY FRIEND” lockup with styling
        val tvLogo = findViewById<TextView>(R.id.tvLogo)
        val logo = "MY\nFRIEND 🐾"
        val span = SpannableStringBuilder(logo).apply {
            // "MY" → slightly smaller, bold
            setSpan(RelativeSizeSpan(0.85f), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.BOLD), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.WHITE), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            // "FRIEND" → bigger, bold
            val startFriend = logo.indexOf("FRIEND")
            val endFriend = startFriend + "FRIEND".length
            setSpan(RelativeSizeSpan(1.35f), startFriend, endFriend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.BOLD), startFriend, endFriend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.WHITE), startFriend, endFriend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tvLogo.text = span
        tvLogo.letterSpacing = 0.06f
        tvLogo.setShadowLayer(8f, 0f, 2f, 0x55000000.toInt())

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(this, Signin::class.java))
        }
        findViewById<MaterialButton>(R.id.btnSignup).setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }
    }
}
