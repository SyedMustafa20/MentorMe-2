package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.TextView

class startinterface : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startinterface)

        var button1=findViewById<ImageButton>(R.id.searchbtn)
        button1.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }
        var button2=findViewById<ImageButton>(R.id.chat1)
        button2.setOnClickListener {
            val intent1 = Intent(this, chats::class.java)
            startActivity(intent1)
        }

        var button3=findViewById<ImageButton>(R.id.profile1)
        button3.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }
        var button4=findViewById<TextView>(R.id.textView26)
        button4.setOnClickListener {
            val intent1 = Intent(this, portfolio::class.java)
            startActivity(intent1)
        }
        var button5=findViewById<ImageButton>(R.id.bellicon)
        button5.setOnClickListener {
            val intent1 = Intent(this, notifications::class.java)
            startActivity(intent1)
        }

        var button6=findViewById<ImageButton>(R.id.addnew)
        button6.setOnClickListener {
            val intent1 = Intent(this, newmentor::class.java)
            startActivity(intent1)
        }
        var button7=findViewById<ImageButton>(R.id.home1)
        button7.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }
    }
}