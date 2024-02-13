package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView

class newmentor : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newmentor)
        val status = arrayOf( "Available", "Unavailable")
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, status)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner3: Spinner = findViewById(R.id.spinner3)
        spinner3.adapter = adapter3

        val button1 = findViewById<ImageButton>(R.id.back8)
        button1.setOnClickListener {
            onBackPressed()
        }
        var button2=findViewById<TextView>(R.id.upload)
        button2.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }
        var button3=findViewById<ImageButton>(R.id.home4)
        button3.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }
        var button4=findViewById<ImageButton>(R.id.searchbtn4)
        button4.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }
        var button5=findViewById<ImageButton>(R.id.chat4)
        button5.setOnClickListener {
            val intent1 = Intent(this, chats::class.java)
            startActivity(intent1)
        }
        var button6=findViewById<ImageButton>(R.id.profile4)
        button6.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }
        var button7=findViewById<ImageButton>(R.id.addnew4)
        button7.setOnClickListener {
            val intent1 = Intent(this, newmentor::class.java)
            startActivity(intent1)
        }
    }
}