package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class Review(
    val id: String,
    val userId: String,
    val mentorId: String,
    val description: String,
    val rating: Float
){
    constructor() : this(
        "",
        "",
        "",
        "",
        0.0f
    )
}

class feedback : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var mentorId: String
    private lateinit var userId: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)


        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        val mentorName = intent.getStringExtra("mentorName")
        val mentorDescription = intent.getStringExtra("mentorDescription")
        val mentorProfileImageUri = intent.getStringExtra("mentorProfileImage")
        val mentorSessionPrice = intent.getDoubleExtra("mentorSessionPrice", 0.0)
        mentorId = intent.getStringExtra("mentorId").toString()
        userId = auth.currentUser?.uid ?: ""

        findViewById<TextView>(R.id.name).text = mentorName

        val imageView = findViewById<ImageView>(R.id.image)
        val requestOptions = RequestOptions().transform(CircleCrop())

        if (!mentorProfileImageUri.isNullOrEmpty()) {
            Glide.with(this)
                .load(Uri.parse(mentorProfileImageUri))
                .apply(requestOptions)
                .into(imageView)
        }

        // Back button click listener
        findViewById<ImageButton>(R.id.back7).setOnClickListener {
            onBackPressed()
        }

        // Submit button click listener
        findViewById<TextView>(R.id.submitfeedback).setOnClickListener {
            submitReview()
        }

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.setOnRatingBarChangeListener { _, ratingValue, _ ->

            updateMentorRating(ratingValue)
        }
    }

    private fun submitReview() {
        val reviewDescription = findViewById<TextView>(R.id.editTextText13).text.toString()
        val newRating = findViewById<RatingBar>(R.id.ratingBar).rating

        val reviewId = databaseReference.child("reviews").push().key ?: ""
        val review = Review(reviewId, userId, mentorId, reviewDescription, newRating)

        databaseReference.child("reviews").child(reviewId).setValue(review)
            .addOnSuccessListener {
                // Review saved successfully
                // Calculate aggregate rating and update mentor's rating in the database
                updateMentorRating(newRating)
                onBackPressed()
            }
            .addOnFailureListener { e ->
                // Failed to save the review
                // You can handle the error here
            }
    }

    private fun updateMentorRating(newRating: Float) {
        val mentorRef = databaseReference.child("mentors").child(mentorId)
        mentorRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get the previous rating from the dataSnapshot
                val previousRating = dataSnapshot.child("rating").getValue(Float::class.java) ?: 0.0f

                // Calculate the new aggregate rating
                val aggregateRating = (previousRating + newRating) / 2

                // Format the aggregate rating to one decimal place
                val formattedRating = String.format("%.1f", aggregateRating.toFloat())

                // Update the 'rating' attribute value in the database with the formatted aggregate rating
                mentorRef.child("rating").setValue(formattedRating.toFloat())
                    .addOnSuccessListener {
                        // Rating updated successfully
                    }
                    .addOnFailureListener { e ->
                        // Failed to update rating
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled event
            }
        })
    }






    private fun updateMentorRatingInDatabase(rating: Float) {

        databaseReference.child("mentors").child(mentorId).child("rating").setValue(rating)
            .addOnSuccessListener {
                // Rating updated successfully
            }
            .addOnFailureListener { e ->
                // Failed to update rating
            }
    }
}
