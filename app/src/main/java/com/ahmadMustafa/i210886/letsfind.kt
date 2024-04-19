package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class letsfind : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
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

        val searchButton = findViewById<ImageView>(R.id.searched)
        val searchEditText = findViewById<EditText>(R.id.editText12)

        searchButton.setOnClickListener {
            val mentorName = searchEditText.text.toString().trim()
            if (mentorName.isNotEmpty()) {
                val intent = Intent(this, Results::class.java)
                intent.putExtra("mentorName", mentorName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter a mentor name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}