package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class cooperCommunity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cooper_community)

        val button1 = findViewById<ImageButton>(R.id.back12)
        button1.setOnClickListener {
            onBackPressed()
        }

        var button2=findViewById<ImageButton>(R.id.home7)
        button2.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }

        var button3=findViewById<ImageButton>(R.id.searchbtn7)
        button3.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }
        var button4=findViewById<ImageButton>(R.id.chat7)
        button4.setOnClickListener {
            val intent1 = Intent(this, chats::class.java)
            startActivity(intent1)
        }
        var button5=findViewById<ImageButton>(R.id.profile7)
        button5.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }
        var button6=findViewById<ImageButton>(R.id.addnew7)
        button6.setOnClickListener {
            val intent1 = Intent(this, newmentor::class.java)
            startActivity(intent1)
        }

        var button7=findViewById<ImageButton>(R.id.voicebtn2)
        button7.setOnClickListener {
            val intent1 = Intent(this, voicecall::class.java)
            startActivity(intent1)
        }
        var button8=findViewById<ImageButton>(R.id.videobtn2)
        button8.setOnClickListener {
            val intent1 = Intent(this, videocall::class.java)
            startActivity(intent1)
        }

        var button9=findViewById<ImageButton>(R.id.cambtn2)
        button9.setOnClickListener {
            val intent1 = Intent(this, photo::class.java)
            startActivity(intent1)
        }
    }
}