package com.example.pfe1.teacher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.task.data.TaskRemote
import com.example.pfe1.task.data.TaskRepositoryImpl
import com.example.pfe1.task.domain.TaskRepository
import com.example.pfe1.task.domain.model.Task
import com.example.pfe1.task.ui.TaskEvents
import com.example.pfe1.task.ui.TasksViewModel
import com.example.pfe1.ui.theme.BrandColor
import com.example.pfe1.ui.theme.introRustBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable

fun ClassScreen(navController: NavController) {

    val viewModel1 = viewModel<TasksViewModel>()
    val TaskRepository: TaskRepository = TaskRepositoryImpl()
    val coroutineScope = rememberCoroutineScope()


    var taskIsAdding by remember { mutableStateOf(0) }

    var name = listOf("Ali", "Akrem", "Mohamed")
    var names = listOf("Fruit And Vegeteb..", "Family Members", "Summer")
    var avatar = listOf(
        "https://static.vecteezy.com/system/resources/previews/020/460/371/large_2x/avatar-of-a-brunei-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/019/887/420/large_2x/avatar-of-a-superhero-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/021/770/058/large_2x/avatar-of-a-student-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/011/997/978/original/cute-and-smile-cartoon-fruit-colorful-character-watermelon-free-png.png"    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandColor.lightGreen)
    ) {
        Text(
            text = "Tasks",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp, 20.dp, 0.dp, 0.dp,),
            fontWeight = FontWeight.Bold,
            fontFamily = introRustBase,
            fontSize = 40.sp

        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(0))
                    .background(BrandColor.lightGreen)


            )
            if (taskIsAdding==0){
            Box(
                modifier = Modifier
                    .weight(7f)
                    .fillMaxHeight()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(10))
                    .background(BrandColor.orange)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopCenter


                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = BrandColor.Green,
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .size(170.dp)
                            .clip(CircleShape),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { taskIsAdding +=1}
                        .padding(0.dp, 0.dp, 0.dp, 50.dp),
                    contentAlignment = Alignment.BottomCenter


                ) {
                    Text(
                        text = "ADD TASK",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { taskIsAdding += 1 },
                        fontWeight = FontWeight.Bold,
                        fontFamily = introRustBase,
                        textAlign = TextAlign.Center,
                        fontSize = 34.sp,
                        color = Color.White

                    )
                }}}



            if (taskIsAdding==1){
            Box(modifier = Modifier
                .weight(7f)
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .clip(RoundedCornerShape(10))
                .background(Color.White)
                .clickable {
                    taskIsAdding+=1
                    coroutineScope.launch {
                        //withContext(Dispatchers.IO) {
                            //val task = TaskRemote()
                            //TaskRepository.addTask(task.toTask())
                        }
                }
           // }
            ) {

                    Column(modifier = Modifier.fillMaxSize()) {for (i in 0..2) {
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
                                    text = names[i],
                                    fontSize = 20.sp,
                                    fontFamily = introRustBase,
                                    color = Color.Black,
                                )
                            }

                        }


            }}}}
            if (taskIsAdding==2){
                Box(modifier = Modifier
                    .weight(7f)
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(10))
                    .background(BrandColor.violet)
                    .clickable { taskIsAdding+=1 },
                    contentAlignment = Alignment.Center
                ) {
                    if (taskIsAdding == 2) {
                        LaunchedEffect(Unit) {
                            delay(3000) // Wait for 5 seconds
                            taskIsAdding = 3
                        }

                        CircularProgressIndicator(
                            modifier = Modifier.size(64.dp).fillMaxSize()
                        )
                    }}}
            if (taskIsAdding==3){
            Box(
                modifier = Modifier
                    .weight(7f)
                    .fillMaxHeight()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(10))
                    .background(BrandColor.violet)
                    .clickable { navController.navigate(Screen.VocabularyTasksScreen.route) }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopCenter


                ) {
                    AsyncImage(
                        model = avatar[3],
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .size(170.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp, 0.dp, 20.dp),
                    contentAlignment = Alignment.BottomStart


                ) {
                    Text(
                        text = "Fruit and \n\nVegetables",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = introRustBase,
                        fontSize = 34.sp,
                        color = Color.White

                    )
                }}}
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .clip(RoundedCornerShape(0))
                        .background(BrandColor.lightGreen)
                )


            }

            Text(
                text = "Students List",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp, 40.dp, 0.dp, 0.dp,),
                fontWeight = FontWeight.Bold,
                fontFamily = introRustBase,
                fontSize = 25.sp

            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(4f)
                    .padding(20.dp, 0.dp, 20.dp, 0.dp,)
                    .padding(10.dp)
                    .border(1.dp, BrandColor.Gray)
                    .clip(RoundedCornerShape(5))
                    .background(Color.White),

                ) {
                for (i in 0..2) {
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
                                    model = avatar[i],
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
                                    text = name[i],
                                    fontSize = 20.sp,
                                    fontFamily = introRustBase,
                                    color = Color.Black,
                                )
                            }

                        }

                    }
                }
            }

        }

}