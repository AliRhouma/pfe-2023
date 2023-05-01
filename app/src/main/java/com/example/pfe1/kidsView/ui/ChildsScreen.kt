package com.example.pfe1.kidsView.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pfe1.kidsView.ui.components.ChildCard
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChildsScreen(){
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
    var avatarIsSelected by remember { mutableStateOf(-1) }


    LaunchedEffect(key1 = addState.isSuccess) {
        if (addState.isSuccess) {
            modalSheetState.hide()
            name = ""
            schoolYear = ""
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
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(22.dp))
                //SchoolYear
                Text(text = "School year:")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    schoolYears.forEachIndexed { index, childSchoolYear ->
                        Column(modifier = Modifier.weight(1f)) {
                            RadioButton(
                                selected = selectedSchoolYear == index,
                                onClick = {
                                    selectedSchoolYear = index
                                    schoolYear = childSchoolYear
                                          },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = childSchoolYear,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(22.dp))
                // Avatar Picker
                Text(text = "Choose an avatar")
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
                                        width = if (avatarIsSelected == index) 4.dp else 1.dp,
                                        color = if (avatarIsSelected == index) Color.Blue else Color.White,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .clickable {
                                        avatarIsSelected = index
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
                                        width = if (avatarIsSelected == index + 3) 4.dp else 1.dp,
                                        color = if (avatarIsSelected == index + 3) Color.Blue else Color.White,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .clickable {
                                        avatarIsSelected = index + 3
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
                        schoolYear = schoolYear,
                        imageUrl = imageUrl
                    ))
                },
                enabled = !addState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = if (addState.isLoading) ButtonDefaults.outlinedButtonColors() else ButtonDefaults.buttonColors()
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
        Scaffold() { paddingValues ->
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

                items(state.childList) { child ->
                    ChildCard(childName = child.name, childSchoolYear = child.schoolYear, imageUrl = child.imageUrl )
                }

                item {
                    TextButton(onClick = {
                        coroutineScope.launch {
                            modalSheetState.show()
                        }
                    }) {
                        Text("Add child")
                    }
                }
            }
        }
    }

    
}