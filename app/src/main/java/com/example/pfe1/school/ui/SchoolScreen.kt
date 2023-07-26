package com.example.pfe1.school.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pfe1.R
import com.example.pfe1.classes.model.Classes
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.ui.ChildEvents
import com.example.pfe1.navigation.Screen
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.GradeColor
import com.example.pfe1.ui.theme.arialUniCode
import com.example.pfe1.ui.theme.introRustBase

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SchoolScreen(
    navController: NavController,
    schoolId: String,
) {
    println("schoooolId1kk11 ${schoolId}")

    val viewModel = viewModel<SchoolViewModel>()

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val coroutineScope = rememberCoroutineScope()


    var name by remember { mutableStateOf("") }
    var schoolYear by remember { mutableStateOf("") }

    var gradeList: List<String> =
        listOf("Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6")


    val classesState by viewModel.classesUiState.collectAsState()
    val addClassesState by viewModel.addClassUiState.collectAsState()
    val schoolNameState by viewModel.schoolUiState.collectAsState()

    var selectedSchoolYear by remember { mutableStateOf(-1) }
    val schoolYears = listOf("1", "2", "3", "4", "5", "6")



    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Class's name") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = BrandColor.Green,
                        unfocusedIndicatorColor = BrandColor.Green
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(22.dp))
                //SchoolYear
                Text(
                    text = "School year:",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    schoolYears.forEachIndexed { index, childSchoolYear ->
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            RadioButton(
                                selected = selectedSchoolYear == index,
                                onClick = {
                                    selectedSchoolYear = index
                                    schoolYear = childSchoolYear
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFF1984A7),
                                    unselectedColor = Color(0xFFA7A7A7)
                                ),
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = childSchoolYear,
                                color = Color.Black,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    onClick = {
                        viewModel.onEvent(
                            SchoolEvents.AddClasses(
                                name = name,
                                schoolId = schoolId,
                                grade = "Grade ${schoolYear.toInt()}"
                            )
                        )
                    },
                    colors =
                    ButtonDefaults.textButtonColors(
                        containerColor = BrandColor.Green,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.4f)
                ) {
                    Text(text = "Add Class")
                }
            }
        }) {


        Column(
            modifier = Modifier
                .padding(3.dp, 10.dp)
                .fillMaxSize()
        ) {
            //Top Part
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp, 3.dp)
                    .fillMaxWidth()
                    .weight(1.5f)
                    .clip(RoundedCornerShape(20))
                    .background(BrandColor.Green)
                    .padding(10.dp)
            ) {
                Text(
                    text = schoolNameState.schoolName,
                    fontSize = 22.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontFamily = introRustBase
                )
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        tint = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 7.dp, 0.dp)
                            .size(40.dp)
                            .clickable {

                                navController.navigate(Screen.SchoolSettingsScreen.route + "?schoolId=${schoolId}")
                            })

            }

            //Second Part
            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .fillMaxSize()
                    .weight(7f)
            ) {
                Card(
                    modifier = Modifier
                        .padding(5.dp, 3.dp)
                        .fillMaxSize()
                        .border(0.dp, BrandColor.Gray, RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = BrandColor.lightGreen,
                        contentColor = Color.White,
                    ),
                ) {
                    Text(
                        text = "Classes",
                        fontFamily = introRustBase,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 10.dp),
                        textAlign = TextAlign.Center,
                        color = BrandColor.Green
                    )
                    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                        for (i in 0..5) {
                            val classesList =
                                mutableListOf<Classes>() // Create a new list for each iteration
                            for (clas in classesState.classesList) {
                                if (gradeList[i] == clas.grade) {
                                    println("ddddddddddd ${gradeList[i] == clas.grade}")
                                    classesList += clas
                                }
                            }
                            item {
                                println("schoooolId222 ${schoolId}")

                                CardGrade(i + 1, classesList,coroutineScope,modalSheetState, navController,schoolId) }
                        }
                    }
                }


            }


            //Third Part
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxSize()
                        .weight(1f)
                        .clickable { navController.navigate(Screen.TeachersScreen.route + "?schoolId=${schoolId}") },
                    shape = RoundedCornerShape(25),
                    colors = CardDefaults.cardColors(
                        containerColor = BrandColor.Green,
                    ),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Teachers",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            fontFamily = arialUniCode,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxSize()
                        .weight(1f)
                        .clickable { navController.navigate(Screen.StudentsScreen.route + "?schoolId=${schoolId}") },
                    shape = RoundedCornerShape(25),
                    colors = CardDefaults.cardColors(
                        containerColor = BrandColor.Green,
                    ),
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                        Text(
                            text = "Students",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            fontFamily = arialUniCode,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }
            }
        }
    }
}    

    
    


