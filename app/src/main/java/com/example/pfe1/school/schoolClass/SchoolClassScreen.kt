package com.example.pfe1.school.schoolClass

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pfe1.R
import com.example.pfe1.navigation.Screen
import com.example.pfe1.school.StudentsUi.StudentsViewModel
import com.example.pfe1.school.ui.SchoolEvents
import com.example.pfe1.task.ui.TasksViewModel
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.introRustBase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable


fun SchoolClassScreen(
    navController: NavController,
    schoolId: String,
    classId: String,
) {





    val viewModel = viewModel<SchoolClassViewModel>()
    val studentsState by viewModel.AllChildsSchoolUiState.collectAsState()


    val childsOfClassState by viewModel.childsSchoolUiState.collectAsState()
    val teachersState by viewModel.teachersSchoolUiState.collectAsState()


    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
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
                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd){
                                        var childIsInClass by remember { mutableStateOf(teachrs in childsOfClassState.studentsList) }
                                        Checkbox(
                                            checked = childIsInClass ,
                                            onCheckedChange = {
                                                if (teachrs in childsOfClassState.studentsList) {
                                                    viewModel.onEvent(SchoolClassEvents.DeleteChild(teachrs.id))
                                                    childIsInClass = false
                                                }
                                                 else
                                                    {viewModel.onEvent(SchoolClassEvents.AddChild(teachrs.id) )
                                                        childIsInClass = true

                                                    }
                                                              },
                                            modifier = Modifier.padding(0.dp,0.dp,15.dp,0.dp,)
                                        
                                        )
                                    }
                                }


                            }

                        }
                    }
                }

        }}){


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BrandColor.lightGreen)
        ) {
            println("schoooolId444 ${schoolId}")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(BrandColor.Green)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 7.dp, 0.dp)
                            .size(40.dp)
                            .clickable {
                                println("schoooolId555 ${schoolId}")
                                navController.navigate(Screen.School.route + "?schoolId=${schoolId}")
                            })
                }
            }
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .background(BrandColor.lightGreen)
            ) {
                Text(
                    text = "Students List",
                    fontFamily = introRustBase,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(17.dp, 12.dp, 0.dp, 0.dp),
                    color = Color.Black
                )
                Box(
                    modifier = Modifier
                        .weight(4f)
                        .padding(10.dp)
                ) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .clip(RoundedCornerShape(5))
                            .background(Color.White),

                        ) {
                        if (childsOfClassState.studentsList.isEmpty()) {
                            println("ooooooooooooo list is empty")
                            item {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "There is no student",
                                        modifier = Modifier.fillMaxSize(),
                                        textAlign = TextAlign.Center,
                                        color = BrandColor.Green,
                                        fontSize = 22.sp
                                    )

                                }
                            }
                        }
                        childsOfClassState.studentsList.mapIndexed { index, teachrs ->
                            println("ooooooooooooo list not empty")
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
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.CenterEnd
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.arrow),
                                                contentDescription = null,
                                                tint = Color(0xFFFFFFFF),
                                                modifier = Modifier
                                                    .padding(10.dp, 0.dp, 7.dp, 0.dp)
                                                    .size(40.dp)
                                                    .clickable {
                                                        println("schoooolId555 ${schoolId}")
                                                        navController.navigate(Screen.School.route + "?schoolId=${schoolId}")
                                                    })
                                            Icon(
                                                painter = painterResource(id = R.drawable.arrow),
                                                contentDescription = null,
                                                tint = Color(0xFFFFFFFF),
                                                modifier = Modifier
                                                    .padding(60.dp, 0.dp, 7.dp, 0.dp)
                                                    .size(40.dp)
                                                    .clickable {
                                                        println("schoooolId555 ${schoolId}")
                                                        navController.navigate(Screen.School.route + "?schoolId=${schoolId}")
                                                    })
                                        }
                                    }


                                }


                            }
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            tint = BrandColor.Green,
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 0.dp, 20.dp)
                                .size(50.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        modalSheetState.show()
                                    }

                                })
                    }


                }
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .background(BrandColor.lightGreen)
            ) {
                Text(
                    text = "Teachers Screen",
                    fontFamily = introRustBase,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(17.dp, 12.dp, 0.dp, 0.dp),
                    color = Color.Black
                )
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(4f)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(5))
                        .background(Color.White),

                    ) {
                    if (teachersState.teachersList.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "There is no teacher",
                                    modifier = Modifier.fillMaxSize(),
                                    textAlign = TextAlign.Center,
                                    color = BrandColor.Green,
                                    fontSize = 22.sp
                                )

                            }
                        }
                    }
                    teachersState.teachersList.mapIndexed { index, teachrs ->
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


                                    Text(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(60.dp, 10.dp, 0.dp, 0.dp,),
                                        text = teachrs.name,
                                        fontSize = 20.sp,
                                        fontFamily = introRustBase,
                                        color = Color.Black,
                                    )
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(

                                            painter = painterResource(id = R.drawable.arrow),
                                            contentDescription = null,
                                            tint = Color(0xFFFFFFFF),
                                            modifier = Modifier
                                                .padding(10.dp, 0.dp, 7.dp, 0.dp)
                                                .size(40.dp)
                                                .clickable {
                                                    println("schoooolId555 ${schoolId}")
                                                    navController.navigate(Screen.School.route + "?schoolId=${schoolId}")
                                                })
                                        Icon(
                                            painter = painterResource(id = R.drawable.arrow),
                                            contentDescription = null,
                                            tint = Color(0xFFFFFFFF),
                                            modifier = Modifier
                                                .padding(60.dp, 0.dp, 7.dp, 0.dp)
                                                .size(40.dp)
                                                .clickable {
                                                    println("schoooolId555 ${schoolId}")
                                                    navController.navigate(Screen.School.route + "?schoolId=${schoolId}")
                                                })
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