package com.example.contactlist.repo

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import com.example.contactlist.model.User
import com.google.firebase.database.*

class FirebaseRepository(val context: Context) {

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = database.getReference("USER")

    fun addUserToFirebase(
        firstName:String,
        lastName:String,
        email:String,
        phoneNumber:String
    ){
        val userId = databaseReference.push().key!!
        val userObject = User(userId,firstName,lastName,email,phoneNumber)
        databaseReference.child(userId).setValue(userObject).addOnSuccessListener {
            Toast.makeText(
                context,
                "Data added to Firebase Database",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener {e->
            Toast.makeText(
                        context,
                        "Fail to add data $e",
                        Toast.LENGTH_SHORT
                    ).show()
        }
    }

    fun deleteData(
        userId:String
    ){
         databaseReference.child(userId).removeValue()
    }

}