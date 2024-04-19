package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        var button2=findViewById<TextView>(R.id.textView10)
        button2.setOnClickListener {
            val intent2 = Intent(this, registerpage::class.java)
            startActivity(intent2)
        }
        var button3=findViewById<TextView>(R.id.textView8)
        button3.setOnClickListener {
            val intent3 = Intent(this, forgetpass::class.java)
            startActivity(intent3)

        }
        auth = FirebaseAuth.getInstance()


        val emailEditText = findViewById<EditText>(R.id.email)
        emailEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailEditText.text = null // Clear the text when EditText gains focus
            }
        }
        val passwordEditText = findViewById<EditText>(R.id.enterpass)
        passwordEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordEditText.text = null // Clear the text when EditText gains focus
            }
        }
        val loginButton = findViewById<TextView>(R.id.loginbutton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT)
                                .show()
                            val sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putBoolean("isLoggedIn", true)
                            editor.apply()
                            val intent = Intent(this, startinterface::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
