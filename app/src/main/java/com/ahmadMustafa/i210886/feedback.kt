package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class feedback : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val button1 = findViewById<ImageButton>(R.id.back7)
        button1.setOnClickListener {
            onBackPressed()
        }
        var button3=findViewById<TextView>(R.id.submitfeedback)
        button3.setOnClickListener {
            val intent1 = Intent(this, portfolio::class.java)
            startActivity(intent1)
        }
    }
}