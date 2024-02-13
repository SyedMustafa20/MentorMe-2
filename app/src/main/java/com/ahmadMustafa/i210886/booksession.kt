package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class booksession : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booksession)


        val button1 = findViewById<ImageButton>(R.id.back9)
        button1.setOnClickListener {
            onBackPressed()
        }

        var button2=findViewById<TextView>(R.id.book)
        button2.setOnClickListener {
            val intent1 = Intent(this, portfolio::class.java)
            startActivity(intent1)
        }
    }
}