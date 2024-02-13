package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class myprofile : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)

        val button1 = findViewById<ImageButton>(R.id.back13)
        button1.setOnClickListener {
            onBackPressed()
        }
        var button2=findViewById<ImageButton>(R.id.editpf1)
        button2.setOnClickListener {
            val intent1 = Intent(this, editprofile::class.java)
            startActivity(intent1)
        }
        var button3=findViewById<ImageButton>(R.id.editpf2)
        button3.setOnClickListener {
            val intent1 = Intent(this, editprofile::class.java)
            startActivity(intent1)
        }
        var button4=findViewById<Button>(R.id.checkbooked)
        button4.setOnClickListener {
            val intent1 = Intent(this, bookedsessions::class.java)
            startActivity(intent1)
        }
        var button5=findViewById<TextView>(R.id.john)
        button5.setOnClickListener {
            val intent1 = Intent(this, portfolio::class.java)
            startActivity(intent1)
        }

        var button6=findViewById<ImageButton>(R.id.home8)
        button6.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }

        var button7=findViewById<ImageButton>(R.id.searchbtn8)
        button7.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }
        var button8=findViewById<ImageButton>(R.id.chat8)
        button8.setOnClickListener {
            val intent1 = Intent(this, chats::class.java)
            startActivity(intent1)
        }
        var button9=findViewById<ImageButton>(R.id.profile8)
        button9.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }
        var button10=findViewById<ImageButton>(R.id.addnew8)
        button10.setOnClickListener {
            val intent1 = Intent(this, newmentor::class.java)
            startActivity(intent1)
        }
    }
}