package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Results : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val button1 = findViewById<ImageButton>(R.id.back5)
        button1.setOnClickListener {
            onBackPressed()
        }
        var button2=findViewById<ImageButton>(R.id.home3)
        button2.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }
        var button3=findViewById<ImageButton>(R.id.searchbtn2)
        button3.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }
        var button4=findViewById<ImageButton>(R.id.chat3)
        button4.setOnClickListener {
            val intent1 = Intent(this, chats::class.java)
            startActivity(intent1)
        }
        var button5=findViewById<ImageButton>(R.id.profile3)
        button5.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }
        var button6=findViewById<ImageButton>(R.id.addnew3)
        button6.setOnClickListener {
            val intent1 = Intent(this, newmentor::class.java)
            startActivity(intent1)
        }
    }
}