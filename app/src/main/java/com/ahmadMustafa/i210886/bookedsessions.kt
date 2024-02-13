package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class bookedsessions : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookedsessions)
        val button1 = findViewById<ImageButton>(R.id.back15)
        button1.setOnClickListener {
            onBackPressed()
        }
    }
}