package com.example.pfe1.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.pfe1.enumClass.UserType
import com.example.pfe1.navigation.Screen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(navController: NavHostController) {

    val viewModel = viewModel<LoginViewModel>()
    val state by viewModel.uiState.collectAsState()

    val auth = Firebase.auth

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    println("ammmm Innnn")

    LaunchedEffect(key1 = state.isSuccess){
        if (state.isSuccess) {
            if (state.userType == UserType.PARENT) {
                navController.navigate(Screen.Childs.route + "?parentId=${auth.currentUser?.uid}")
            }
            else if (state.userType == UserType.TEACHER) {
                navController.navigate(Screen.Teacher.route + "?teacherId=${auth.currentUser?.uid}")
            }
            else if (state.userType == UserType.SCHOOL) {
                navController.navigate(Screen.School.route+"?schoolId=${auth.currentUser?.uid}")
            }
            else {
                navController.navigate(Screen.Login.route)
                println("bbbbbbbbbbb")
            }

        }
    }
    println("ammmm Innnn toooo")
    println(Firebase.auth.currentUser == null)

    var isLoginCheckLoading by remember { mutableStateOf(false) }

   /* LaunchedEffect(key1 = true) {
         if (Firebase.auth.currentUser != null) {
            println("state is ${state.userType} ")
            if (state.userType == UserType.PARENT) {
                navController.navigate(Screen.Childs.route + "?parentId=${auth.currentUser?.uid}")
            }
            else if (state.userType == UserType.TEACHER) {
                println("hani mena")
                navController.navigate(Screen.Teacher.route  + "?teacherId=${auth.currentUser?.uid}")
            }
            else if (state.userType == UserType.SCHOOL) {
                navController.navigate(Screen.School.route+"?schoolId=${auth.currentUser?.uid}")

            }
            else {
                println("aaaaaaaaaaaaaaaaaaa ${Firebase.auth.currentUser!!.uid}")
            }
        } else {
            isLoginCheckLoading = false
        }
    }*/

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()

    ) {
        if (isLoginCheckLoading) {
            CircularProgressIndicator()
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )


println("hani 7dha email")
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                println("hani 7dha pass")

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                Button(
                    onClick = {
                        viewModel.onEvent(LoginEvents.Login(email, password))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Login")
                }

                TextButton(
                    onClick = {
                        navController.navigate(Screen.Register.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Register")
                }



                //if (state.isLoading) {
                  //  CircularProgressIndicator()
                //}djnddksnnjnjnjnljnjnjnjklnnnknknjknknnnlnlkkkkknknzodzodzodzjlnjjjnjksndjsnj

              if (state.error != null) {
                   Text(state.error ?: "")
                }
                println("hani 7dha if")

            }
        }
    }
}

@Composable
@Preview
fun preview(){
    //LoginScreen()
}


