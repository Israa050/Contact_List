package com.example.contactlist.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contactlist.R
import com.example.contactlist.model.User
import com.example.contactlist.navigation.Screens
import com.example.contactlist.ui.theme.Teal200
import com.example.contactlist.viewmodel.SharedViewModel
import com.google.firebase.database.FirebaseDatabase


@Composable
fun DetailScreen(navController: NavHostController, userId:String,sharedViewModel: SharedViewModel) {

    var email by remember {
        mutableStateOf("")
    }

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    val openDialog = remember { mutableStateOf(false)  }
    var userObject  = User()

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = database.getReference("USER")
            databaseReference.child(userId).get().addOnSuccessListener(){
                if(it.exists()){
                    email = it.child("email").value.toString()
                    firstName = it.child("firstName").value.toString()
                    lastName= it.child("lastName").value.toString()
                    phoneNumber = it.child("phoneNumber").value.toString()
                }
            }.addOnFailureListener {_->
               // Toast.makeText(context,e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        userObject = User(userId,firstName,lastName,email,phoneNumber)

        Column(
            modifier = Modifier
                .height(400.dp)
                .width(100.dp)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "icon",
                modifier = Modifier.size(220.dp),
            )

            Text(
                text = "${userObject.firstName} ${userObject.lastName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

          Row(modifier = Modifier
              .fillMaxWidth()
              .padding(top = 10.dp)
              .size(150.dp),
              horizontalArrangement = Arrangement.Center

          ){
              Button(onClick = {
                     navController.navigate(Screens.EditScreen.passUserData(userId,firstName,lastName,email,phoneNumber))
              },
                  colors = ButtonDefaults.buttonColors(containerColor = Teal200)
              ) {
                  Icon(imageVector = Icons.Default.Edit , contentDescription ="Icon")
              }

              Spacer(
                  modifier = Modifier
                      .width(4.dp)
              )

              Button(
                  onClick = {
                      openDialog.value = true
                  },
                   colors = ButtonDefaults.buttonColors(containerColor = Teal200)
              ) {
                  Icon(imageVector = Icons.Default.Delete , contentDescription ="Icon")
                  AlertDialogSample(openDialog = openDialog, userObject = userObject,navController,sharedViewModel)
              }
          }

        }

        Spacer(
            modifier = Modifier
                .width(4.dp)
        )

        Column(
            modifier = Modifier
                .height(50.dp)
                .width(70.dp)
                .padding(top = 70.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "* Phone Number ",
                 fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp)
                )

            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )

            Text(text = " ${userObject.phoneNumber} ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(5.dp),
                color = Color.Black

            )

            Spacer(modifier = Modifier
                .width(10.dp)
                .size(10.dp))


            Text(text = "* Email ",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp)
            )

            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )

            Text(text = " ${userObject.email} ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(5.dp),
                color = Color.Black

            )

        }
    }

}

@Composable
fun AlertDialogSample(openDialog:MutableState<Boolean>,userObject:User?,navController: NavHostController,sharedViewModel: SharedViewModel) {
    MaterialTheme {
        Column {
            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Delete")
                    },
                    text = {
                        Text("Do you want to delete ${userObject!!.firstName} ${userObject!!.lastName} from the list?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                                //dbRef.removeValue()
                                sharedViewModel.deleteData(userObject?.userId)
                                navController.popBackStack()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Teal200)
                        ) {
                            Text("Delete")

                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Teal200)
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }

    }
}







@Composable
@Preview
fun preview() {
}