package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class resetpass : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpass)

        auth = FirebaseAuth.getInstance()

        var button = findViewById<ImageButton>(R.id.back3)
        button.setOnClickListener {
            onBackPressed()
        }

        var button2 = findViewById<TextView>(R.id.textView32)
        button2.setOnClickListener {
            resetPassword()
        }

        var button3 = findViewById<TextView>(R.id.button15)
        button3.setOnClickListener {
            val intent3 = Intent(this, loginPage::class.java)
            startActivity(intent3)
        }
    }

    @SuppressLint("WrongViewCast")
    private fun resetPassword() {
        val newPasswordEditText = findViewById<EditText>(R.id.editTextText10)
        val confirmPasswordEditText = findViewById<EditText>(R.id.resetpass)

        val newPassword = newPasswordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (newPassword != confirmPassword) {
            // Show error message or toast indicating passwords do not match
            return
        }

        val user: FirebaseUser? = auth.currentUser

        user?.updatePassword(newPassword)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent3 = Intent(this, loginPage::class.java)
                    startActivity(intent3)
                    // Password updated successfully
                    // You may redirect the user to a success page or perform other actions
                } else {
                    val intent3 = Intent(this, resetpass::class.java)
                    startActivity(intent3)
                    // Password update failed
                    // You may display an error message to the user
                }
            }
    }
}