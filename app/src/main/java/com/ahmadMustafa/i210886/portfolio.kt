package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class portfolio : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)
        var button1=findViewById<Button>(R.id.button21)
        button1.setOnClickListener {
            val intent1 = Intent(this, feedback::class.java)
            startActivity(intent1)
        }
        var button2=findViewById<Button>(R.id.button22)
        button2.setOnClickListener {
            val intent1 = Intent(this, cooperCommunity::class.java)
            startActivity(intent1)
        }
        val button3 = findViewById<ImageButton>(R.id.back6)
        button3.setOnClickListener {
            onBackPressed()
        }
        var button4=findViewById<TextView>(R.id.button23)
        button4.setOnClickListener {
            val intent1 = Intent(this, booksession::class.java)
            startActivity(intent1)
        }
    }
}