package com.example.contactlist.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
//import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactlist.model.User
import com.example.contactlist.navigation.Screens
import com.example.contactlist.ui.theme.Teal200
import com.example.contactlist.viewmodel.SharedViewModel
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.material.ButtonColors
import androidx.compose.ui.graphics.Color

@Composable
fun EditScreen(navHostController: NavHostController,
               userId:String,
               firstName:String,
               lastName:String,
               email:String,
               phoneNumber:String) {
    var firstName by remember {
        mutableStateOf(firstName)
    }

    var lastName by remember {
        mutableStateOf(lastName)
    }

    var email by remember {
        mutableStateOf(email)
    }

    var phoneNumber by remember {
        mutableStateOf(phoneNumber)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        OutlinedTextField(
            value = firstName,
            onValueChange = {
                firstName = it
            },
            label = {
                Text(text = "First Name",
                     color = Color.Black
                    )
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                    )
                }
            },

            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Person ,
                        contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
            },
            label = {
                Text(text = "Last Name",
                    color = Color.Black
                )
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                    )
                }
            },

            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Person ,
                        contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )


        Spacer(modifier = Modifier.height(24.dp))


        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email",
                    color = Color.Black
                )
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                    )
                }
            },

            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "",
                    )
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )


        )

        Spacer(modifier = Modifier.height(24.dp))


        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            label = {
                Text(text = "Phone",
                    color = Color.Black
                )
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                    )
                }
            },

            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "",
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            )

        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val userObject = User(userId,firstName,lastName,email,phoneNumber)
            val dbRef = FirebaseDatabase.getInstance().getReference("USER").child(userId)
                dbRef.setValue(userObject)
            navHostController.popBackStack()
        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Teal200) ) {
            Text(text = "Save Changes")
        }

    }

}