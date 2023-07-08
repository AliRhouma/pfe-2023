package com.example.pfe1.teacher.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.pfe1.navigation.Screen
import com.example.pfe1.school.ui.CardGrade
import com.example.pfe1.school.ui.SchoolViewModel
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.GradeColor
import com.example.pfe1.ui.theme.arialUniCode
import com.example.pfe1.ui.theme.introRustBase

@Composable
fun TeacherScreen(navController: NavController, teacherId: String) {
    val viewModel = viewModel<TeacherViewModel>()
    val teacherState by viewModel.teacherUiState.collectAsState()

    val viewModel1 = viewModel<SchoolViewModel>()
    val classesState by viewModel1.classesUiState.collectAsState()

    var gradeList: List<String> =
        listOf("Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6")



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
                text = teacherState.teacher.name,
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

                        navController.navigate(Screen.SchoolSettingsScreen.route + "?teacherId=${teacherId}")
                    })

        }

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
                    for (i in 0..1) {
                        item {
                            Card(modifier = Modifier
                                .padding(8.dp, 6.dp)
                                .fillMaxWidth()
                                .clickable { navController.navigate(Screen.ClassScreen.route) }
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = LinearOutSlowInEasing
                                    )
                                ),
                                shape = RoundedCornerShape(13.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor =
                                    when (1) {
                                        1 -> GradeColor.orange
                                        2 -> GradeColor.lightBlue
                                        3 -> GradeColor.violet
                                        4 -> GradeColor.orange
                                        5 -> GradeColor.lightBlue
                                        6 -> GradeColor.violet
                                        else -> GradeColor.violet
                                    },
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .size(70.dp)
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            Text(
                                                text = "Grade" + (i + 1).toString(),
                                                color = Color.Black,
                                                fontFamily = arialUniCode,
                                                fontSize = 20.sp
                                            )
                                        }
                                    }

                                }
                            }

                            }
                        }
                    }
                }
            }


        }
    }


