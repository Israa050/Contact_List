package com.example.contactlist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.contactlist.view.AddContact
import com.example.contactlist.view.DetailScreen
import com.example.contactlist.view.EditScreen
import com.example.contactlist.view.Home
import com.example.contactlist.viewmodel.SharedViewModel

@Composable
fun SetUpNavGraph(navController: NavHostController) {

    val viewModel : SharedViewModel = SharedViewModel(LocalContext.current)

    NavHost(navController = navController ,
        startDestination = Screens.Home.route
    ){
        composable(route = Screens.Home.route){
            Home(navController)
        }

        composable(route = Screens.AddContact.route){
            AddContact(navController, sharedViewModel = viewModel)
        }

        composable(
            route = Screens.DetailScreen.route,
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
            })
        ){
            val userId = it.arguments?.getString("id").toString()
            DetailScreen(navController,userId,viewModel)
        }

        composable(
            route = Screens.EditScreen.route,
            arguments = listOf(
                navArgument("id"){NavType.StringType},
                navArgument("firstName"){NavType.StringType},
                navArgument("lastName"){NavType.StringType},
                navArgument("email"){NavType.StringType},
                navArgument("phoneNumber"){NavType.StringType},
            )
            ){
            val userId = it.arguments?.getString("id").toString()
            val firstName = it.arguments?.getString("firstName").toString()
            val lastName = it.arguments?.getString("lastName").toString()
            val email = it.arguments?.getString("email").toString()
            val phoneNumber = it.arguments?.getString("phoneNumber").toString()
            EditScreen(navController,userId,firstName,lastName, email, phoneNumber)
        }
    }

}