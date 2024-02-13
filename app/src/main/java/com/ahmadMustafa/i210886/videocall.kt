package com.ahmadMustafa.i210886

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class videocall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videocall)

        val button1 = findViewById<TextView>(R.id.cutcall)
        button1.setOnClickListener {
            onBackPressed()
        }
    }
}