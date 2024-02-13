package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class resetpass : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpass)
        var button1=findViewById<TextView>(R.id.button15)
        button1.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }
        var button2=findViewById<TextView>(R.id.textView37)
        button2.setOnClickListener {
            val intent2 = Intent(this, loginPage::class.java)
            startActivity(intent2)
        }
        val button3 = findViewById<ImageButton>(R.id.back3)
        button3.setOnClickListener {
            onBackPressed()
        }

    }
}