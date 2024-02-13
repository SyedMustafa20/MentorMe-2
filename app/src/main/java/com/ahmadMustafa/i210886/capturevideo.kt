package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class capturevideo : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capturevideo)

        val button1 = findViewById<TextView>(R.id.cross2)
        button1.setOnClickListener {
            onBackPressed()
        }
        var button2=findViewById<Button>(R.id.campic)
        button2.setOnClickListener {
            val intent1 = Intent(this, photo::class.java)
            startActivity(intent1)
        }
        var button3=findViewById<ImageButton>(R.id.picbtn2)
        button3.setOnClickListener {
            val intent1 = Intent(this, photo::class.java)
            startActivity(intent1)
        }
        val button4 = findViewById<ImageButton>(R.id.capturevid)
        button4.setOnClickListener {
            onBackPressed()
        }
    }
}