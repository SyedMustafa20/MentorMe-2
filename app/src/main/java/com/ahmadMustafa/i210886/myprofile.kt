package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.UUID

class myprofile : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var userNameTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var profileImageView: ImageView
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprofile)

        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Users").child(auth.currentUser!!.uid)

        userNameTextView = findViewById(R.id.textView91)
        cityTextView = findViewById(R.id.city)
        profileImageView = findViewById(R.id.pfp2)

        val logoutButton = findViewById<TextView>(R.id.textView90)
        logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, loginPage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        val backButton = findViewById<ImageButton>(R.id.back13)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val editProfileButton = findViewById<ImageButton>(R.id.editpf1)
        editProfileButton.setOnClickListener {
            val intent = Intent(this, editprofile::class.java)
            startActivity(intent)
        }

        val checkBookedButton = findViewById<Button>(R.id.checkbooked)
        checkBookedButton.setOnClickListener {
            val intent = Intent(this, bookedsessions::class.java)
            startActivity(intent)
        }

        val portfolioButton = findViewById<TextView>(R.id.john)
        portfolioButton.setOnClickListener {
            val intent = Intent(this, portfolio::class.java)
            startActivity(intent)
        }

        val homeButton = findViewById<ImageButton>(R.id.home8)
        homeButton.setOnClickListener {
            val intent = Intent(this, startinterface::class.java)
            startActivity(intent)
        }

        val searchButton = findViewById<ImageButton>(R.id.searchbtn8)
        searchButton.setOnClickListener {
            val intent = Intent(this, letsfind::class.java)
            startActivity(intent)
        }

        val chatButton = findViewById<ImageButton>(R.id.chat8)
        chatButton.setOnClickListener {
            val intent = Intent(this, chats::class.java)
            startActivity(intent)
        }

        val myProfileButton = findViewById<ImageButton>(R.id.profile8)
        myProfileButton.setOnClickListener {
            val intent = Intent(this, myprofile::class.java)
            startActivity(intent)
        }

        val addNewButton = findViewById<ImageButton>(R.id.addnew8)
        addNewButton.setOnClickListener {
            val intent = Intent(this, newmentor::class.java)
            startActivity(intent)
        }

        // Load user data from Firebase
        loadUserData()
    }

    private fun loadUserData() {
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userName = dataSnapshot.child("name").getValue(String::class.java)
                    val city = dataSnapshot.child("city").getValue(String::class.java)
                    val profilePictureUrl =
                        dataSnapshot.child("profilePicture").getValue(String::class.java)

                    userNameTextView.text = userName
                    cityTextView.text = city

                }

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            // Check if the request code is the same as the PICK_IMAGE_REQUEST
            if (requestCode == PICK_IMAGE_REQUEST) {
                // Get the URI of the selected image
                imageUri = data?.data

                // Set the image view to the selected image and make it round
                val imageView = findViewById<ShapeableImageView>(R.id.pfp)
                val radius = resources.getDimension(R.dimen.corner)
                imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, radius)
                    .build()

                imageView.setImageURI(imageUri)

                // Upload the image to Firebase Storage


                    }
                }
            }
        }
