package com.example.pfe1.register.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pfe1.enumClass.UserType
import com.example.pfe1.navigation.Screen

@Composable
fun RegisterScreen(navController: NavHostController) {
    val viewModel = viewModel<RegisterViewModel>()
    val state by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf(UserType.DEFAULT) }


    LaunchedEffect(key1 = state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Screen.Login.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                RadioButton(
                    selected = userType == UserType.TEACHER,
                    onClick = {
                        userType = UserType.TEACHER
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF1984A7),
                        unselectedColor = Color(0xFFA7A7A7)
                    ),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Teacher",
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                RadioButton(
                    selected = userType == UserType.SCHOOL,
                    onClick = {
                        userType = UserType.SCHOOL
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF1984A7),
                        unselectedColor = Color(0xFFA7A7A7)
                    ),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "School",
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(22.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                RadioButton(
                    selected = userType == UserType.PARENT,
                    onClick = {
                        userType = UserType.PARENT
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF1984A7),
                        unselectedColor = Color(0xFFA7A7A7)
                    ),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Parent",
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }



        Spacer(modifier = Modifier.height(22.dp))


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
                viewModel.register(name, email, password, userType)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Register")
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(state.error ?: "")
        }
    }
}

@Composable
@Preview
fun preview(){
    //LoginScreen()
}


