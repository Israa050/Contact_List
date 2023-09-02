package com.example.contactlist.view

import android.content.Context
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.contactlist.model.User
import com.example.contactlist.navigation.Screens
import com.example.contactlist.ui.theme.Teal200
import com.example.contactlist.viewmodel.SharedViewModel
@Composable
fun AddContact(navController: NavHostController,sharedViewModel: SharedViewModel) {

    var context = LocalContext.current

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
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
            sharedViewModel.addData(firstName,lastName,email,phoneNumber)
            navController.popBackStack()
        }, enabled = firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Teal200)
            ) {
            Text(text = "Save")
        }

    }
}