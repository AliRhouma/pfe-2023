package com.example.pfe1.kidsView.ui.parentSettingsScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pfe1.navigation.Screen
import com.example.pfe1.register.data.UserRemote
import com.example.pfe1.school.ui.SchoolViewModel
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.arialUniCode
import com.example.pfe1.ui.theme.introRustBase
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentSettingsScreen(navController: NavController, parentId:String) {
    val viewModel = viewModel<ParentSettingViewModel>()

    val schoolSettingsState by viewModel.parentSettingsUiState.collectAsState()

    var school: UserRemote = schoolSettingsState.parent

    var name by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandColor.Green)
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .background(BrandColor.Green), contentAlignment = Alignment.Center
        ) {
            Text(
                text = schoolSettingsState.parent.name,
                modifier = Modifier
                    .padding(20.dp, 20.dp, 20.dp, 0.dp,)
                    .fillMaxWidth(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = introRustBase,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .weight(7f)
                .padding(20.dp, 0.dp, 20.dp, 20.dp,)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SParent Name",
                modifier = Modifier
                    .padding(40.dp, 20.dp, 20.dp, 0.dp,)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = arialUniCode,
                color = BrandColor.Gray
            )


            TextField(
                value = name,
                onValueChange = { name = it },
                label = { },

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = BrandColor.Green,
                    unfocusedIndicatorColor = Color.Black,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(60.dp, 0.dp, 60.dp, 0.dp)
                    .fillMaxWidth(),
                // .height(30.dp),
                // placeholder = { Text("Enter student ID",fontSize = 40.sp) },
            )

            Text(
                text = "Email",
                modifier = Modifier
                    .padding(40.dp, 60.dp, 20.dp, 0.dp,)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = arialUniCode,
                color = BrandColor.Gray
            )

            TextField(
                value = school.email,
                onValueChange = { },
                label = { },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = BrandColor.Green,
                    unfocusedIndicatorColor = Color.Black,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(60.dp, 0.dp, 60.dp, 0.dp)
                    .fillMaxWidth(),
                // .height(30.dp),
                // placeholder = { Text("Enter student ID",fontSize = 40.sp) },
            )

            Text(
                text = "Password",
                modifier = Modifier
                    .padding(40.dp, 60.dp, 20.dp, 0.dp,)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = arialUniCode,
                color = BrandColor.Gray
            )

            TextField(
                value = school.name,
                onValueChange = { school.name = it },
                label = { },

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = BrandColor.Green,
                    unfocusedIndicatorColor = Color.Black,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(60.dp, 0.dp, 60.dp, 0.dp)
                    .fillMaxWidth(),
                // .height(30.dp),
                // placeholder = { Text("Enter student ID",fontSize = 40.sp) },
            )

            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {

                    Card(
                        modifier = Modifier
                            .padding(60.dp,20.dp,60.dp,20.dp,)
                            .fillMaxWidth()
                            .size(50.dp)
                            .clickable {

                                viewModel.onEvent(
                                    ParentSettingEvents.UpdateParentData(parentId, name)
                                )
                                navController.popBackStack(
                                    Screen.Childs.route + "?parentId=$parentId",
                                    false
                                )
                            },
                        shape = RoundedCornerShape(25),
                        colors = CardDefaults.cardColors(
                            containerColor = BrandColor.Green,
                        ),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Save",
                                modifier = Modifier,
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp,
                                fontFamily = arialUniCode,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,

                                )
                        }
                    }


                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {

                    Card(
                        modifier = Modifier
                            .padding(60.dp,0.dp,60.dp,20.dp,)
                            .fillMaxWidth()
                            .size(50.dp)
                            .border(2.dp,Color.Red, RoundedCornerShape(25))
                            .clickable {

                                FirebaseAuth.getInstance().signOut()

                                navController.navigate(
                                    Screen.Login.route
                                )
                            },
                        shape = RoundedCornerShape(25),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Logout",
                                modifier = Modifier,
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp,
                                fontFamily = arialUniCode,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,

                                )
                        }
                    }


                }
            }
        }
    }
}


