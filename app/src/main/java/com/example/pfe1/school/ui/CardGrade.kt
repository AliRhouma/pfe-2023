package com.example.pfe1.school.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pfe1.R
import com.example.pfe1.classes.model.Classes
import com.example.pfe1.navigation.Screen
import com.example.pfe1.ui.theme.GradeColor
import com.example.pfe1.ui.theme.arialUniCode
import com.example.pfe1.ui.theme.introRustBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardGrade(grade: Int, classes: List<Classes> = listOf(), coroutineScope: CoroutineScope,modalSheetState: ModalBottomSheetState, navController: NavController, schoolId: String) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 90f else -90f
    )
    Card(modifier = Modifier
        .padding(8.dp, 6.dp)
        .fillMaxWidth()
        .clickable { expandedState = !expandedState }
        .animateContentSize(
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = RoundedCornerShape(13.dp),
        colors = CardDefaults.cardColors(
            containerColor =
            when (grade) {
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
                contentAlignment = Alignment.CenterEnd
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 7.dp, 0.dp)
                        .size(20.dp),
                    onClick = { expandedState = !expandedState },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = null,
                        tint = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .rotate(rotationState)
                    )
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text =
                    when (grade) {
                        1 -> "Grade 1"
                        2 -> "Grade 2"
                        3 -> "Grade 3"
                        4 -> "Grade 4"
                        5 -> "Grade 5"
                        6 -> "Grade 6"
                        else -> "Grade 0"
                    },
                    color = Color.Black,
                    fontFamily = arialUniCode
                    )
            }
        }

        if (expandedState) {
            classes.mapIndexed { index, classes ->
                Divider(color = Color.Black)
                TextButton(
                    onClick = {
                        println("schoooolId333 ${schoolId}")
                              navController.navigate(Screen.SchoolClassScreen.route + "?schoolId=${schoolId}&&classId=${classes.id}" )
                    }, Modifier.fillMaxWidth().background(Color.White)
                ) {
                    Text(
                        text = classes.name,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        fontFamily = arialUniCode,
                        fontWeight = FontWeight.Bold,
                    )

                }
            }
            Divider(color = Color.Black)
            TextButton(
                modifier = Modifier.fillMaxWidth().background(Color.White),
                onClick = {
                    coroutineScope.launch {
                        modalSheetState.show()
                    }
                }
            ) {
                Text(
                    text = "Add Class",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontFamily = arialUniCode,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,

                    )


            }


        }


    }


}
