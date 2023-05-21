package com.example.pfe1.kidsView.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.ui.components.BottomNavigationBar
import com.example.pfe1.kidsView.ui.components.ChildCard
import com.example.pfe1.navigation.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun ChildsScreen(
    navController: NavController,
    parentId: String,
    paddingValues: PaddingValues,
){
    val navItems = listOf(
        BottomNavItem("Home", "",Icons.Filled.Home),
        BottomNavItem("Profile","", Icons.Filled.Person),
        BottomNavItem("Settings","", Icons.Filled.Settings)
    )
    var selectedTab by remember { mutableStateOf(0) }


    val pageState by remember {
        mutableStateOf(0)
    }

    val viewModel = viewModel<ChildViewModel>()

    val state by viewModel.uiState.collectAsState()
    val addState by viewModel.addChildUiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var schoolYear by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    var selectedSchoolYear by remember { mutableStateOf(-1) }
    val schoolYears = listOf("1","2","3","4","5","6")

    val avatarList = listOf(
        "https://static.vecteezy.com/system/resources/previews/020/460/371/large_2x/avatar-of-a-brunei-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/019/887/420/large_2x/avatar-of-a-superhero-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/021/770/058/large_2x/avatar-of-a-student-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/020/460/373/large_2x/avatar-of-a-brunei-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/021/770/047/large_2x/avatar-of-a-junior-school-indonesian-character-free-vector.jpg",
        "https://static.vecteezy.com/system/resources/previews/020/194/530/large_2x/avatar-of-a-character-with-casual-outfit-free-vector.jpg"
    )
    var selectedAvatar by remember { mutableStateOf(-1) }


    LaunchedEffect(key1 = addState.isSuccess) {
        if (addState.isSuccess) {
            modalSheetState.hide()
            name = ""
            selectedSchoolYear = -1
            selectedAvatar = -1
            viewModel.onEvent(ChildEvents.ClearAddChild)
        }
    }

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
                    label = { Text("Child's name") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color(0xFFBED9E4),
                        unfocusedIndicatorColor = Color(0xFFBED9E4)
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
                            horizontalAlignment = CenterHorizontally

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
                // Avatar Picker
                Text(
                    text = "Choose an avatar",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                //avatarPicker
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    avatarList.subList(0, 3).forEachIndexed { index, avatarUrl ->
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = avatarUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(72.dp)
                                    .border(
                                        width = if (selectedAvatar == index) 4.dp else 1.dp,
                                        color = if (selectedAvatar == index) Color(0xFF1984A7) else Color.White,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .clickable {
                                        selectedAvatar = index
                                        imageUrl = avatarUrl
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    avatarList.subList(3, 6).forEachIndexed { index, avatarUrl ->
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = avatarUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(72.dp)
                                    .border(
                                        width = if (selectedAvatar == index + 3) 4.dp else 1.dp,
                                        color = if (selectedAvatar == index + 3) Color(0xFF1984A7) else Color.White,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .clickable {
                                        selectedAvatar = index + 3
                                        imageUrl = avatarUrl
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            Button(
                onClick = {
                    viewModel.onEvent(ChildEvents.AddChild(
                        name = name,
                        schoolYear = SchoolYear.values()[schoolYear.toInt()],
                        imageUrl = imageUrl
                    ))
                },
                colors =
                if (addState.isLoading)
                    ButtonDefaults.outlinedButtonColors()
                else
                    ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFFBED9E4),
                        contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .align(CenterHorizontally)
                    .fillMaxWidth(0.4f),
                enabled = !addState.isLoading,
            ) {
                if (addState.isLoading){
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Text(if (addState.isLoading) "Adding child..." else "Add child")
            }

            if (addState.errorMessage != null) {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = { viewModel.onEvent(ChildEvents.ClearAddChild) }
                        ) {
                            Text("Dismiss")
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(addState.errorMessage!!)
                }
            }
        }
    ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp)
            ) {
                if (state.isLoading){
                    item {
                        CircularProgressIndicator()
                    }
                }

                state.errorMessage?.let {
                    item {
                        Text(it)
                    }
                }

                if (state.childList.isEmpty()
                    && !state.isLoading
                    && state.errorMessage == null) {
                    item {
                        Text(text = "There is no childs")
                    }
                }

                /////////////////////////////////////////////////////////////////
                state.childList.forEach() { child ->

                        item {
                            ChildCard(
                                childName = child.name,
                                childSchoolYear = child.schoolYear,
                                imageUrl = child.imageUrl,
                                id = child.id,
                                navController = navController
                            )
                        }
                    }



                item {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                modalSheetState.show()
                            }
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color(0xFFBED9E4),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(0.4f)
                    ) {
                        Text(
                            text = "Add child",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }
            }
        }
    }
