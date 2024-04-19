package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage


class myprofile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    private lateinit var usernameTextView: TextView
    private lateinit var locationTextView: TextView

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { selectedImage ->
            uploadImageToFirebaseStorage(selectedImage)
        }
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        storage = Firebase.storage

        usernameTextView = findViewById(R.id.textView91)
        locationTextView = findViewById(R.id.city)

        val buttonEditProfile = findViewById<ImageButton>(R.id.editpf2)
        buttonEditProfile.setOnClickListener {
            openGalleryForImage()
        }

        val logoutButton = findViewById<TextView>(R.id.textView90)
        logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, loginPage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        val button5 = findViewById<ImageButton>(R.id.editpf1)
        button5.setOnClickListener {
            val intent5 = Intent(this, editprofile::class.java)
            startActivity(intent5)
        }

        val button4 = findViewById<ImageButton>(R.id.back13)
        button4.setOnClickListener {
            onBackPressed()
        }

        // Add button listeners for other buttons
        val buttonHome = findViewById<ImageButton>(R.id.home8)
        buttonHome.setOnClickListener {
            val intent11 = Intent(this, startinterface::class.java)
            startActivity(intent11)
        }

        val bookedSession = findViewById<TextView>(R.id.checkbooked)
        bookedSession.setOnClickListener {
            val intent11 = Intent(this, bookedsessions::class.java)
            startActivity(intent11)
        }

        val buttonSearch = findViewById<ImageButton>(R.id.searchbtn8)
        buttonSearch.setOnClickListener {
            val intent1 = Intent(this, letsfind::class.java)
            startActivity(intent1)
        }

        val buttonChat = findViewById<ImageButton>(R.id.chat8)
        buttonChat.setOnClickListener {
            val intent6 = Intent(this, chats::class.java)
            startActivity(intent6)
        }

        val buttonMyProfile = findViewById<ImageButton>(R.id.profile8)
        buttonMyProfile.setOnClickListener {
            val intent4 = Intent(this, myprofile::class.java)
            startActivity(intent4)
        }

        val buttonAdd = findViewById<ImageButton>(R.id.addnew8)
        buttonAdd.setOnClickListener {
            val intent3 = Intent(this, newmentor::class.java)
            startActivity(intent3)
        }

        // Fetch and display user profile information
        displayUserProfile()
    }

    private fun openGalleryForImage() {
        getContent.launch("image/*")
    }

    @SuppressLint("SuspiciousIndentation")
    private fun uploadImageToFirebaseStorage(imageUri: Uri) {
        // Upload image to Firebase Storage
    }

    private fun displayUserProfile() {
        Log.d("myprofile", "displayUserProfile() called")
        val userId = auth.currentUser?.uid
        userId?.let { uid ->
            val userRef = database.reference.child("users").child(uid)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("myprofile", "onDataChange()")
                    val username = snapshot.child("name").getValue(String::class.java)
                    val location = snapshot.child("city").getValue(String::class.java)
                    val imageUrl = snapshot.child("profilePicture").getValue(String::class.java)
                    Log.d("myprofile", "Username: $username, Location: $location, Image URL: $imageUrl")

                    // Set fetched username and location to TextViews
                    username?.let { usernameTextView.text = it }
                    location?.let { locationTextView.text = it }

                    // Load user image if available
                    imageUrl?.let { url -> Glide.with(this@myprofile)
                            .load(url)
                            .circleCrop() // Apply circular transformation
                            .into(findViewById(R.id.pfp2)) // Assuming the ImageView's ID is profileImageView
                        }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseDatabase", "Error fetching user profile: ${error.message}")
                }
            })
        }
    }
}