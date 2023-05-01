package com.example.pfe1.navigation

sealed class Screen(
    val route : String,
){
    object Login : Screen(route = "login")
    object Subjects : Screen(route  =  "subjects")
    object Register : Screen(route = "register")
    object Childs : Screen(route = "childs")


}
