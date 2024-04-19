package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase



class UserData(
    var name: String,
    var email: String,
    var contactNumber: String,
    var country: String,
    var city: String,
    var password: String,
    val profilePicture: String? = ""

){

    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}

class registerpage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registerpage)

        auth = FirebaseAuth.getInstance()

        val items = arrayOf("Select Country", "Pakistan", "India", "Afghanistan")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner1: Spinner = findViewById(R.id.spinner1)
        spinner1.adapter = adapter

        val items2 = arrayOf("Select City", "Islamabad", "Karachi", "Lahore")
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items2)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner2: Spinner = findViewById(R.id.spinner2)
        spinner2.adapter = adapter2

        val signUpButton = findViewById<TextView>(R.id.button2)
        signUpButton.setOnClickListener {
                val name = findViewById<TextView>(R.id.name).text.toString()
                val email = findViewById<TextView>(R.id.email).text.toString()
                val contactNumber = findViewById<TextView>(R.id.contact).text.toString()
                val country = spinner1.selectedItem.toString()
                val city = spinner2.selectedItem.toString()
                val password = findViewById<TextView>(R.id.password).text.toString()

                val addOnCompleteListener = auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val userData =
                                UserData(name, email, contactNumber, country, city, password)
                            FirebaseDatabase.getInstance().getReference("users")
                                .child(user!!.uid)
                                .setValue(userData)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(
                                            "TAG",
                                            "User registration and data storage successful"
                                        )
                                        Toast.makeText(
                                            baseContext,
                                            "Registration Successful.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE)
                                        val editor = sharedPreferences.edit()
                                        editor.putBoolean("isLoggedIn", true)
                                        editor.apply()
                                        startActivity(Intent(this, startinterface::class.java))
                                        finish()
                                    } else {
                                        Log.w("TAG", "Data storage failed", task.exception)
                                        Toast.makeText(
                                            baseContext,
                                            "User authentication failed.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        // Handle database storage failure
                                    }
                                }
                        } else {
                            Log.w("TAG", "User registration failed", task.exception)
                            Toast.makeText(
                                baseContext,
                                "User authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Handle authentication failure
                        }
                    }
            }

        val loginButton = findViewById<TextView>(R.id.loginbtn)
        loginButton.setOnClickListener {
            startActivity(Intent(this, loginPage::class.java))
        }
    }
}
