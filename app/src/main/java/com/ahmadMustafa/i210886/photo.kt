package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class photo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)


        val button1 = findViewById<TextView>(R.id.textView81)
        button1.setOnClickListener {
            onBackPressed()
        }
        var button2=findViewById<Button>(R.id.vidbtn)
        button2.setOnClickListener {
            val intent1 = Intent(this, capturevideo::class.java)
            startActivity(intent1)
        }
        var button3=findViewById<ImageButton>(R.id.vidbtn2)
        button3.setOnClickListener {
            val intent1 = Intent(this, capturevideo::class.java)
            startActivity(intent1)
        }
        val button4 = findViewById<ImageButton>(R.id.capturepic)
        button4.setOnClickListener {
            onBackPressed()
        }

    }
}