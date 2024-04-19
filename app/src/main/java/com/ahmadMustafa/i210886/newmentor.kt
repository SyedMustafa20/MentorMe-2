package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class Mentor (
    val id: String,
    val name: String,
    val title: String,
    val description: String,
    val imagePath: String,
    val sessionPrice: Double,
    val availability: String,
    val rating: Double
){

    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        0.0,
        "",
        0.0
    )
}
class newmentor : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storageReference: FirebaseStorage
    private var urival: Uri? =null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newmentor)

        auth = FirebaseAuth.getInstance()
        storageReference = FirebaseStorage.getInstance()

        val items = arrayOf("Available", "Not Available")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = findViewById(R.id.spinner3)
        spinner.adapter = adapter

        val button1 = findViewById<ImageButton>(R.id.back8)
        button1.setOnClickListener {
            onBackPressed()
        }

        val homeButton = findViewById<ImageButton>(R.id.home4)
        homeButton.setOnClickListener {
            startActivity(Intent(this, startinterface::class.java))
        }

        val searchButton = findViewById<ImageButton>(R.id.searchbtn4)
        searchButton.setOnClickListener {
            startActivity(Intent(this, letsfind::class.java))
        }

        val myProfileButton = findViewById<ImageButton>(R.id.profile4)
        myProfileButton.setOnClickListener {
            startActivity(Intent(this,myprofile::class.java))
        }

        val chatButton = findViewById<ImageButton>(R.id.chat4)
        chatButton.setOnClickListener {
            startActivity(Intent(this, chats::class.java))
        }
        val uploadButton = findViewById<TextView>(R.id.textView69)
        uploadButton.setOnClickListener {
            val name = findViewById<TextView>(R.id.editname).text.toString()
            val description = findViewById<TextView>(R.id.description).text.toString()
            val status = spinner.selectedItem.toString()

            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please enter name and description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Launch image selection when image view is clicked
            selectImage()
        }

        val submitButton = findViewById<TextView>(R.id.upload)
        submitButton.setOnClickListener {
            uploadMentorData(urival.toString())
            startActivity(Intent(this, startinterface::class.java))
        }
    }

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { uri ->
            uploadImageAndSaveMentor(uri)
        }
    }
    fun selectImage() {
        getContent.launch("image/*")
    }




    private fun uploadImageAndSaveMentor(imageUri: Uri) {
        // Upload image to Firebase Storage
        val filename = UUID.randomUUID().toString()
        val imageRef = storageReference.reference.child("images/$filename")

        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            // Image uploaded successfully, get the download URL
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                // Pass image URL to uploadMentorData function
                urival = uri

            }.addOnFailureListener { exception ->
                // Handle failure to get image download URL
                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { exception ->
            // Handle image upload failure
            Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadMentorData(imageUrl: String) {
        // Retrieve mentor data
        val name = findViewById<TextView>(R.id.editname).text.toString()
        val description = findViewById<TextView>(R.id.description).text.toString()
        val status = findViewById<Spinner>(R.id.spinner3).selectedItem.toString()

        // Create Mentor object
        val mentor = Mentor(
            id = UUID.randomUUID().toString(),
            name = name,
            title = "",
            description = description,
            imagePath = imageUrl, // Image URL
            sessionPrice = 0.0,
            availability = status,
            rating = 0.0
        )

        // Get reference to the Firebase database
        val database = FirebaseDatabase.getInstance()
        val mentorsRef = database.getReference("mentors")

        // Push mentor data to Firebase Realtime Database
        mentorsRef.child(mentor.id).setValue(mentor)
            .addOnSuccessListener {
                val intent = Intent(this, portfolio::class.java).apply {
                    putExtra("mentorName", mentor.name)
                    putExtra("mentorId", mentor.id)
                    putExtra("mentorTitle", mentor.title)
                    putExtra("mentorRating", mentor.rating)
                    putExtra("mentorsessionPrice", mentor.sessionPrice)
                    putExtra("mentorDescription", mentor.description)
                    putExtra("mentorProfileImage", mentor.imagePath)
                }

                // Start MainActivity11 with mentor details
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                // Handle failure to upload mentor data
                Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}