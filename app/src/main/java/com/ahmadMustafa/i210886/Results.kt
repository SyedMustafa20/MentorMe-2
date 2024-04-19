package com.ahmadMustafa.i210886

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Results : AppCompatActivity() {
    private lateinit var resultMentorList: ArrayList<Mentor>
    private lateinit var resultMentorAdapter: searchAdapter
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().reference.child("mentors")

        setupSpinner()
        setupRecyclerView()
        setupButtonListeners()
        retrieveAndSetMentorData()
    }

    private fun setupSpinner() {
        val items = arrayOf("Filter", "aaa", "bbbb")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner: Spinner = findViewById(R.id.spinner3)
        spinner.adapter = adapter
    }

    private fun setupRecyclerView() {
        resultMentorList = ArrayList()
        resultMentorAdapter = searchAdapter(resultMentorList, this)
        val recyclerView: RecyclerView = findViewById(R.id.searchResult)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = resultMentorAdapter
    }

    private fun setupButtonListeners() {
        findViewById<ImageButton>(R.id.back5).setOnClickListener {
            onBackPressed()
        }

        findViewById<ImageButton>(R.id.home3).setOnClickListener {
            startActivity(Intent(this, startinterface::class.java))
        }

        findViewById<ImageButton>(R.id.addnew3).setOnClickListener {
            startActivity(Intent(this, newmentor::class.java))
        }

        findViewById<ImageButton>(R.id.profile3).setOnClickListener {
            startActivity(Intent(this, myprofile::class.java))
        }

        findViewById<ImageButton>(R.id.chat3).setOnClickListener {
            startActivity(Intent(this, chats::class.java))
        }

        findViewById<ImageButton>(R.id.searchbtn2).setOnClickListener {
            startActivity(Intent(this, letsfind::class.java))
        }
    }

    private fun retrieveAndSetMentorData() {
        val mentorName = intent.getStringExtra("mentorName")

        if (!mentorName.isNullOrEmpty()) {
            // Query Firebase for mentor data by name
            databaseReference.orderByChild("name").equalTo(mentorName)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot in dataSnapshot.children) {
                            val mentor = snapshot.getValue(Mentor::class.java)
                            if (mentor != null) {
                                // Clear the list and add the mentor
                                resultMentorList.clear()
                                resultMentorList.add(mentor)
                                // Notify the adapter of the data change
                                resultMentorAdapter.notifyDataSetChanged()
                                break
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle error
                    }
                })
        }
    }
}