package com.example.pfe1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pfe1.login.ui.LoginScreen
import com.example.pfe1.register.ui.RegisterScreen
import com.example.pfe1.subjects.ui.SubjectsScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(
            route = Screen.Login.route
        ){
            LoginScreen(navController)
        }

        composable(
            route = Screen.Subjects.route
        ){
            SubjectsScreen()
        }

        composable(
            route = Screen.Register.route
        ){
            RegisterScreen(navController)
        }

    }
}