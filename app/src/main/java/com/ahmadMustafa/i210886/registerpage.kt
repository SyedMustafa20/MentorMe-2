package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class registerpage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerpage)
        val country = arrayOf("Select Country","Pakistan", "India", "China")
        val city = arrayOf("Select City","Islamabad", "Karachi", "Lahore")
// Create an ArrayAdapter using the string array and a default spinner layout
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, country)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, city)
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
// Apply the adapter to the spinner
        val spinner1: Spinner = findViewById(R.id.spinner1)
        spinner1.adapter = adapter1
        val spinner2: Spinner = findViewById(R.id.spinner2)
        spinner2.adapter = adapter2

        var button1=findViewById<TextView>(R.id.button2)
        button1.setOnClickListener {
            val intent1 = Intent(this, verifyNumber::class.java)
            startActivity(intent1)
        }
        var button2=findViewById<TextView>(R.id.textView21)
        button2.setOnClickListener {
            val intent2 = Intent(this, loginPage::class.java)
            startActivity(intent2)
        }
    }
}