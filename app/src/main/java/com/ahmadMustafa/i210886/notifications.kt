package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.database.*


data class Notification(
    val id: String,
    val message: String,
    val timestamp: Long
)

class notifications : AppCompatActivity() {

    private lateinit var notificationAdapter: notificationAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseReference: DatabaseReference
    private lateinit var notifications: MutableList<Notification>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        recyclerView = findViewById(R.id.notification)
        recyclerView.layoutManager = LinearLayoutManager(this)
        notifications = mutableListOf()
        notificationAdapter = notificationAdapter(notifications)
        recyclerView.adapter = notificationAdapter

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().reference

        // Set up click listener for home button
        val homeButton: ImageButton = findViewById(R.id.back16)
        homeButton.setOnClickListener {
            // Navigate to the last activity
            onBackPressed()
        }

        // Start the foreground service to listen for updates
        startForegroundService()
    }

    private fun startForegroundService() {
        val serviceIntent = Intent(this, FirebaseUpdateService::class.java)
        startService(serviceIntent)
    }
}