package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.UUID

data class Booking(
    val id: String,
    val userId: String,
    val mentorId: String,
    val date: String,
    val time: String

){
    constructor() : this(
        "",
        "",
        "",
        "",
        ""
    )
}
class booksession : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private lateinit var backButton: ImageButton
    private lateinit var book: TextView
    private lateinit var timeslot1: TextView
    private lateinit var timeslot2: TextView
    private lateinit var timeslot3: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var sessionPriceTextView: TextView

    private lateinit var selectedDate: String
    private var selectedTime: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booksession)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("mentors")

        backButton = findViewById(R.id.back9)
        book = findViewById(R.id.book)
        timeslot1 = findViewById(R.id.textView71)
        timeslot2 = findViewById(R.id.textView72)
        timeslot3 = findViewById(R.id.textView73)
        ratingTextView = findViewById(R.id.textView58)
        sessionPriceTextView = findViewById(R.id.textView57)

        backButton.setOnClickListener {
            onBackPressed()
        }

        timeslot1.setOnClickListener {
            selectTime("10:00 AM")
        }

        timeslot2.setOnClickListener {
            selectTime("11:00 AM")
        }

        timeslot3.setOnClickListener {
            selectTime("12:00 PM")
        }

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // month is 0-based, so add 1 for the actual month
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        }

        // Retrieve mentor details from intent
        val mentorName = intent.getStringExtra("mentorName")
        val mentorProfileImageUri = intent.getStringExtra("mentorProfileImage")
        val mentorId = intent.getStringExtra("mentorId")

        findViewById<TextView>(R.id.name).text = mentorName
        val imageView = findViewById<ImageView>(R.id.image)
        val requestOptions = RequestOptions().transform(CircleCrop())

        if (!mentorProfileImageUri.isNullOrEmpty()) {
            Glide.with(this)
                .load(Uri.parse(mentorProfileImageUri))
                .apply(requestOptions)
                .into(imageView)
        }

        book.setOnClickListener {
            bookAppointment()
        }

        // Load mentor's rating from the database
        mentorId?.let { loadMentorRating(it) }
        mentorId?.let { loadSessionPrice(it) }
    }

    private fun selectTime(time: String) {
        // Update selected time and UI accordingly
        selectedTime = time
        timeslot1.isSelected = time == "10:00 AM"
        timeslot2.isSelected = time == "11:00 AM"
        timeslot3.isSelected = time == "12:00 PM"
    }

    private fun bookAppointment() {
        val mentorId = intent.getStringExtra("mentorId")
        val userId = auth.currentUser?.uid

        if (userId != null && mentorId != null) {
            // Check if a date and time are selected
            if (!::selectedDate.isInitialized || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select both date and time", Toast.LENGTH_SHORT).show()
                return
            }

            // Proceed with booking
            val bookingId = UUID.randomUUID().toString()
            val booking = Booking(bookingId, userId, mentorId, selectedDate, selectedTime)

            // Save booking to the database
            databaseReference.child(mentorId).child("bookings").child(bookingId).setValue(booking)
                .addOnSuccessListener {
                    Toast.makeText(this@booksession, "Appointment booked successfully", Toast.LENGTH_SHORT).show()
                        val intent2 = Intent(this, bookedsessions::class.java)
                        startActivity(intent2)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@booksession, "Failed to book appointment: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this@booksession, "User ID or Mentor ID is null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadMentorRating(mentorId: String) {
        databaseReference.child(mentorId).child("rating").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val mentorRating = dataSnapshot.getValue(Float::class.java)
                ratingTextView.text = mentorRating?.toString() ?: "0.0"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@booksession, "Failed to load mentor rating: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadSessionPrice(mentorId: String) {
        databaseReference.child(mentorId).child("sessionPrice").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val sessionPrice = dataSnapshot.getValue(Double::class.java)
                sessionPriceTextView.text = sessionPrice?.toString() ?: "0.0"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@booksession, "Failed to load session price: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}