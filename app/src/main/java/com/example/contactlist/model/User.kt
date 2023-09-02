package com.example.contactlist.model

import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.MutableStateFlow

data class User(
    var userId:String? = null,
    var firstName:String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null
)
