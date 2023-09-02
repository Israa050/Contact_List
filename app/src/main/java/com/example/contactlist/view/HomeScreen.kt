package com.example.contactlist.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.contactlist.R
import com.example.contactlist.model.User
import com.example.contactlist.navigation.Screens
import com.example.contactlist.ui.theme.Teal200
import com.example.contactlist.viewmodel.SharedViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch


@Composable
fun Home(navController: NavHostController) {

    var userList = remember {
        mutableStateListOf<User>()
    }

    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("USER")
    databaseReference.get().addOnSuccessListener(){
        if(it.exists()){
            userList.clear()
            it.children.forEach { it ->
                val userId = it.key
                val email =  it.child("email").value.toString()
                val firstName = it.child("firstName").value.toString()
                val lastName = it.child("lastName").value.toString()
                val phoneNumber = it.child("phoneNumber").value.toString()
                val user = User(userId,firstName,lastName,email,phoneNumber)
                userList.add(user)
            }
        }
    }.addOnFailureListener {e->
      //  Toast.makeText(context,e.message.toString(), Toast.LENGTH_SHORT).show()
    }

    var fabHeight by remember {
        mutableStateOf(0)
    }

    val heightInDp = with(LocalDensity.current) { fabHeight.toDp() }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)

    ) {
        Text(text = "My Contacts (${userList.size})",
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(10.dp),
            color = Color.Black
        )

        Scaffold(
            backgroundColor = Color.LightGray,
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.onGloballyPositioned {
                        fabHeight = it.size.height
                    },
                    shape = CircleShape,
                    onClick = {
                        navController.navigate(route = Screens.AddContact.route)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "icon")
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { it ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentPadding = PaddingValues(bottom = heightInDp + 16.dp)
            ) {
                items(userList){user->
                    list_item(user = user,navController)
                }
            }
        }

    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun list_item(user: User,navController: NavHostController) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(5.dp),
        onClick = {
             navController.navigate(route = "detail_screen/" + user.userId)
        }
    )
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_people_2), contentDescription = null,
                modifier = Modifier.clip(CircleShape)
            )

            Spacer(
                modifier = Modifier
                    .width(12.dp)
                    .padding(10.dp)
            )

            Text(
                text = user.firstName!! + " " + user.lastName!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
