package com.example.contactlist.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactlist.model.User
import com.example.contactlist.repo.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel(context: Context):ViewModel() {

    var repo : FirebaseRepository

    init {
        repo = FirebaseRepository(context)
    }

    fun addData(
        firstName:String,
        lastName:String,
        email:String,
        phoneNumber:String
    ){
        repo.addUserToFirebase(firstName,lastName,email,phoneNumber)
    }

    fun deleteData(userId:String?){
        repo.deleteData(userId!!)
    }

}