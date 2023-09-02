package com.example.contactlist.navigation

sealed class Screens(var route:String){
    object Home:Screens(route = "home_screen")
    object AddContact:Screens(route = "add_contact_screen")
    object DetailScreen:Screens(route = "detail_screen/{id}")
    object EditScreen:Screens(route = "edit_screen/{id}/{firstName}/{lastName}/{email}/{phoneNumber}")

    fun passUserData(
        id:String,
        firstName:String,
        lastName:String,
        email:String,
        phoneNumber:String
    ):String{
        return "edit_screen/$id/$firstName/$lastName/$email/$phoneNumber"
    }
}