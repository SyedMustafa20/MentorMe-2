package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class portfolio : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var mentorId: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().reference

        // Retrieve mentor details from intent
        val mentorName = intent.getStringExtra("mentorName")
        val mentorDescription = intent.getStringExtra("mentorDescription")
        val mentorProfileImageUriString = intent.getStringExtra("mentorProfileImage")
        mentorId = intent.getStringExtra("mentorId") ?: ""
        val mentorSessionPrice = intent.getDoubleExtra("mentorsessionPrice", 0.0)
        val mentorTitle = intent.getStringExtra("mentorTitle")

        // Set mentor details in the appropriate fields
        findViewById<TextView>(R.id.name).text = mentorName
        findViewById<TextView>(R.id.textView56).text = mentorTitle
        findViewById<TextView>(R.id.textView60).text = mentorDescription

        // Set rounded corners for profile image
        val imageView = findViewById<ImageView>(R.id.image)
        val requestOptions = RequestOptions().transform(CircleCrop())

        // Load mentor profile image using Glide
        val mentorProfileImageUri = mentorProfileImageUriString?.let { Uri.parse(it) }
        if (mentorProfileImageUri != null) {
            Glide.with(this)
                .load(mentorProfileImageUri)
                .apply(requestOptions)
                .into(imageView)
        }

        // Set up back button click listener
        val backButton = findViewById<ImageButton>(R.id.back6)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Set up button click listeners for other actions
        val bookSessionButton = findViewById<TextView>(R.id.button23)
        bookSessionButton.setOnClickListener {
            navigateTobooksession(mentorName, mentorDescription, mentorProfileImageUriString, mentorSessionPrice)
        }

        val reviewButton = findViewById<TextView>(R.id.button21)
        reviewButton.setOnClickListener {
            navigateToreview(mentorName, mentorDescription, mentorProfileImageUriString)
        }

        val communityButton = findViewById<TextView>(R.id.button22)
        communityButton.setOnClickListener {
            navigateTocommunity(mentorName)
        }

        // Load mentor's rating from the database
        loadMentorRating()
    }

    private fun navigateTobooksession(mentorName: String?, mentorDescription: String?, mentorProfileImageUriString: String?, mentorSessionPrice: Double) {
        val intent = Intent(this, booksession::class.java).apply {
            putExtra("mentorName", mentorName)
            putExtra("mentorDescription", mentorDescription)
            putExtra("mentorProfileImage", mentorProfileImageUriString)
            putExtra("mentorsessionPrice", mentorSessionPrice)
            putExtra("mentorId", mentorId)
        }
        startActivity(intent)
    }

    private fun navigateToreview(mentorName: String?, mentorDescription: String?, mentorProfileImageUriString: String?) {
        val intent = Intent(this, feedback::class.java).apply {
            putExtra("mentorName", mentorName)
            putExtra("mentorDescription", mentorDescription)
            putExtra("mentorProfileImage", mentorProfileImageUriString)
            putExtra("mentorId", mentorId)
        }
        startActivity(intent)
    }

    private fun navigateTocommunity(mentorName: String?) {
        val intent = Intent(this, cooperCommunity::class.java).apply {
            putExtra("mentorName", mentorName)
            putExtra("mentorId", mentorId)
        }
        startActivity(intent)
    }

    private fun loadMentorRating() {
        databaseReference.child("mentors").child(mentorId).child("rating").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mentorRating = dataSnapshot.getValue(Float::class.java)
                findViewById<TextView>(R.id.textView58).text = mentorRating?.toString() ?: "0.0"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }}