package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView

class letsfind : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letsfind)
        val button1 = findViewById<ImageButton>(R.id.back4)
        button1.setOnClickListener {
            onBackPressed()
        }

        var button2=findViewById<TextView>(R.id.entreprenuer)
        button2.setOnClickListener {
            val intent1 = Intent(this, Results::class.java)
            startActivity(intent1)
        }
        var button7=findViewById<ImageButton>(R.id.home2)
        button7.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }
        var button6=findViewById<ImageButton>(R.id.searchpg)
        button6.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }
        var button5=findViewById<ImageButton>(R.id.chat2)
        button5.setOnClickListener {
            val intent1 = Intent(this, chats::class.java)
            startActivity(intent1)
        }
        var button4=findViewById<ImageButton>(R.id.profile2)
        button4.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }
        var button3=findViewById<ImageButton>(R.id.addnew2)
        button3.setOnClickListener {
            val intent1 = Intent(this, newmentor::class.java)
            startActivity(intent1)
        }

    }
}