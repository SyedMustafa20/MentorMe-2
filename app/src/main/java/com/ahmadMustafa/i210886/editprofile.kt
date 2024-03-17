package com.ahmadMustafa.i210886

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class editprofile : AppCompatActivity() {

    private lateinit var countrySpinner: Spinner
    private lateinit var citySpinner: Spinner
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var storageRef: StorageReference
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)

        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().reference.child("Users").child(auth.currentUser!!.uid)
        storageRef = FirebaseStorage.getInstance().reference

        val country = arrayOf("Pakistan", "India", "China")
        val city = arrayOf("Islamabad", "Karachi", "Lahore")

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, country)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, city)

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        countrySpinner = findViewById(R.id.spinner4)
        citySpinner = findViewById(R.id.spinner5)

        countrySpinner.adapter = adapter1
        citySpinner.adapter = adapter2

        val backButton = findViewById<ImageButton>(R.id.back14)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val updateButton = findViewById<TextView>(R.id.update)
        updateButton.setOnClickListener {
            val inputName = findViewById<TextView>(R.id.editTextText15).text.toString()
            val inputEmail = findViewById<TextView>(R.id.newemail).text.toString()
            val inputContactNumber = findViewById<TextView>(R.id.newcontact).text.toString()
            val inputCountry = countrySpinner.selectedItem.toString()
            val inputCity = citySpinner.selectedItem.toString()

            updateUserData(inputName, inputEmail, inputContactNumber, inputCountry, inputCity)
        }

        val selectImageButton = findViewById<ShapeableImageView>(R.id.pfp)
        selectImageButton.setOnClickListener {
            openFileChooser()
        }

        loadUserData()
    }

    private fun loadUserData() {
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val name = snapshot.child("name").getValue(String::class.java)
                    val email = snapshot.child("email").getValue(String::class.java)
                    val contactNumber = snapshot.child("contactNumber").getValue(String::class.java)
                    val country = snapshot.child("country").getValue(String::class.java)
                    val city = snapshot.child("city").getValue(String::class.java)

                    populateFields(name, email, contactNumber, country, city)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun populateFields(name: String?, email: String?, contactNumber: String?, country: String?, city: String?) {
        findViewById<TextView>(R.id.editTextText15).text = name
        findViewById<TextView>(R.id.newemail).text = email
        findViewById<TextView>(R.id.newcontact).text = contactNumber

        val countryIndex = (countrySpinner.adapter as ArrayAdapter<String>).getPosition(country)
        val cityIndex = (citySpinner.adapter as ArrayAdapter<String>).getPosition(city)

        countrySpinner.setSelection(countryIndex)
        citySpinner.setSelection(cityIndex)
    }

    private fun updateUserData(name: String, email: String, contactNumber: String, country: String, city: String) {
        databaseRef.apply {
            child("name").setValue(name)
            child("email").setValue(email)
            child("contactNumber").setValue(contactNumber)
            child("country").setValue(country)
            child("city").setValue(city)
        }

        if (imageUri != null) {
            val fileReference = storageRef.child("profile_pictures").child("${auth.currentUser!!.uid}.jpg")
            fileReference.putFile(imageUri!!)
                .addOnSuccessListener { taskSnapshot ->
                    // Get the download URL from the task snapshot
                    fileReference.downloadUrl.addOnSuccessListener { uri ->
                        // Store the download URL in the database
                        databaseRef.child("profilePicture").setValue(uri.toString())
                            .addOnSuccessListener {
                                Toast.makeText(this@editprofile, "Profile picture uploaded", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this@editprofile, "Failed to upload profile picture", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this@editprofile, "Failed to upload profile picture", Toast.LENGTH_SHORT).show()
                }
        }

        // Redirect to the profile activity
        startActivity(Intent(this@editprofile, myprofile::class.java))
    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
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
                val ref = storageRef.child("images/${UUID.randomUUID()}")
                val uploadTask = ref.putFile(imageUri!!)
                uploadTask.addOnSuccessListener {
                    // Get the download URL
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        // Save the download URL to the Firebase Database
                        val user = FirebaseAuth.getInstance().currentUser
                        val userRef = databaseRef.child(user!!.uid).child("UserInfo")
                        userRef.child("profilePicture").setValue(uri.toString())

                        // Update the shared preferences with the new image URL
                        val sharedPreferences: SharedPreferences = getSharedPreferences("ProfilePicture", MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("profilePictureUri", uri.toString())
                        editor.apply()
                    }
                }
            }
        }
    }
}
