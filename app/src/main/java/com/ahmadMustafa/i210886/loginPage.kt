package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView

class loginPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        //clicking on login
        var button1=findViewById<TextView>(R.id.loginbutton)
        button1.setOnClickListener {
            val intent1 = Intent(this, startinterface::class.java)
            startActivity(intent1)
        }
        //clicking on signup
        var button2=findViewById<TextView>(R.id.textView10)
        button2.setOnClickListener {
            val intent2 = Intent(this, registerpage::class.java)
            startActivity(intent2)
        }
        //clicking on forgot password
        //clicking on signup
        var button3=findViewById<TextView>(R.id.textView8)
        button3.setOnClickListener {
            val intent3 = Intent(this, forgetpass::class.java)
            startActivity(intent3)

        }
    }
}