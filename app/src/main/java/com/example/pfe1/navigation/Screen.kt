package com.example.pfe1.navigation

sealed class Screen(
    val route : String,
){
    object Login : Screen(route = "login")
    object Subjects : Screen(route  =  "subjects")
    object Register : Screen(route = "register")
    object Childs : Screen(route = "childs")
    object ChildHome : Screen(route = "childHome")
    object Tasks : Screen(route = "tasks")
    object Question : Screen(route = "question")
    object Settings : Screen(route = "settings")


}
