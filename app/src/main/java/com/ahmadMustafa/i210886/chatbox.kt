package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class chatbox : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbox)

        val button1 = findViewById<ImageButton>(R.id.back11)
        button1.setOnClickListener {
            onBackPressed()
        }

        var button2=findViewById<ImageButton>(R.id.home6)
        button2.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }

        var button3=findViewById<ImageButton>(R.id.searchbtn6)
        button3.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }
        var button4=findViewById<ImageButton>(R.id.chat6)
        button4.setOnClickListener {
            val intent1 = Intent(this, chats::class.java)
            startActivity(intent1)
        }
        var button5=findViewById<ImageButton>(R.id.profile6)
        button5.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }
        var button6=findViewById<ImageButton>(R.id.addnew6)
        button6.setOnClickListener {
            val intent1 = Intent(this, newmentor::class.java)
            startActivity(intent1)
        }

        var button7=findViewById<ImageButton>(R.id.voicebtn)
        button7.setOnClickListener {
            val intent1 = Intent(this, voicecall::class.java)
            startActivity(intent1)
        }
        var button8=findViewById<ImageButton>(R.id.videobtn)
        button8.setOnClickListener {
            val intent1 = Intent(this, videocall::class.java)
            startActivity(intent1)
        }

        var button9=findViewById<ImageButton>(R.id.cambtn)
        button9.setOnClickListener {
            val intent1 = Intent(this, photo::class.java)
            startActivity(intent1)
        }
    }
}