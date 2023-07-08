package com.example.pfe1.school.studentsUi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pfe1.school.StudentsUi.StudentsEvents
import com.example.pfe1.school.StudentsUi.StudentsViewModel
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.introRustBase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(navController: NavController,studentId: String) {

    val viewModel = viewModel<StudentsViewModel>()

    val studentsState by viewModel.studentsUiState.collectAsState()

    var isAddingStudent by remember { mutableStateOf(false) }
    var studentId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandColor.lightGreen)
    ) {
        Text(
            text = "Students",
            fontFamily = introRustBase,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            textAlign = TextAlign.Center,
            color = BrandColor.Green
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(6f)
                .padding(10.dp)
                .clip(RoundedCornerShape(5))
                .background(Color.White),

            ) {
            if (studentsState.studentsList.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "List is Empty",
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center,
                            color = BrandColor.Green,
                            fontSize = 22.sp
                        )

                    }
                }
            }

            studentsState.studentsList.mapIndexed { index, teachrs ->
                item {
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {

                            AsyncImage(
                                model = teachrs.imageUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 0.dp)
                                    .size(50.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(60.dp, 10.dp, 0.dp, 0.dp,),
                                text = teachrs.name,
                                fontSize = 20.sp,
                                fontFamily = introRustBase,
                                color = Color.Black,
                            )
                        }


                    }

                }
            }
        }

        Row(
            modifier = Modifier
                .background(BrandColor.lightGreen)
                .weight(0.6f)
                .clickable { isAddingStudent = true }
                .padding(10.dp, 0.dp, 10.dp, 10.dp)
                .clip(RoundedCornerShape(20))
                .background(if (!isAddingStudent) BrandColor.Green else BrandColor.lightGreen)
            )
        {
            if (isAddingStudent) {
                TextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = BrandColor.Green,
                        unfocusedIndicatorColor = Color.Black,

                    ),
                    shape = RoundedCornerShape(20),

                    modifier = Modifier
                        .padding(6.dp, 6.dp, 6.dp, 6.dp)
                        .weight(6f)
                        .height(50.dp),
                    placeholder = { Text("Enter student ID",fontSize = 10.sp) },
                    )

                Card(
                    onClick = {
                        viewModel.onEvent(StudentsEvents.AddStudents(studentId))
                        isAddingStudent = false
                        studentId = ""
                    },
                    modifier = Modifier.weight(2f).padding(0.dp,6.dp,0.dp,6.dp).height(50.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = BrandColor.Green
                    )


                ) {
                    Text("Add", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            } else {
                Text(
                    text = "Add student",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



