package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView

class editprofile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)
        val country = arrayOf("Pakistan", "India", "China")
        val city = arrayOf("Islamabad", "Karachi", "Lahore")
// Create an ArrayAdapter using the string array and a default spinner layout
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, country)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, city)
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
// Apply the adapter to the spinner
        val spinner1: Spinner = findViewById(R.id.spinner4)
        spinner1.adapter = adapter1
        val spinner2: Spinner = findViewById(R.id.spinner5)
        spinner2.adapter = adapter2

        val button1 = findViewById<ImageButton>(R.id.back14)
        button1.setOnClickListener {
            onBackPressed()
        }
        var button2=findViewById<TextView>(R.id.update)
        button2.setOnClickListener {
            val intent1 = Intent(this, myprofile::class.java)
            startActivity(intent1)
        }

    }
}