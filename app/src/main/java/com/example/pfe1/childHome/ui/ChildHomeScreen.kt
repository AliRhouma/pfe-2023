package com.example.pfe1.childHome.ui

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pfe1.navigation.Screen
import com.example.pfe1.ui.theme.BlueTheme

@Composable
fun ChildHomeScreen(
    navController: NavController,
    childId: String,
) {
    val viewModel = viewModel<ChildHomeViewModel>()
    val state by viewModel.uiState.collectAsState()

    val childName = viewModel.onEvent(ChildHomeEvents.GetChildData(childId))

    Scaffold() {paddingValues ->



    Column(
            modifier = androidx.compose.ui.Modifier
                .padding(paddingValues)
                .background(Color(0xFF1984A7))
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            else if (state.errorMessage != null) { }
else {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                AsyncImage(
                    model = state.child.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = "Welcome ${state.child.name}",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = BlueTheme.lightBlue
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "id: ${state.child.studentId}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = BlueTheme.lightBlue
                    ),
                    textAlign = TextAlign.Center
                )

            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .weight(3.2f)
                    .padding(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .weight(1f)
                            .height(260.dp)
                    ) {
                        ChildHomeCard("Subjects", "", Screen.Subjects.route+"?childId=${childId}", navController)
                    }
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .weight(1f)
                    ) {
                        ChildHomeCard("Chat", "", Screen.Subjects.route, navController,)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .weight(1f)
                    ) {
                        ChildHomeCard("Rank", "", Screen.Subjects.route, navController)
                    }
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .weight(1f)
                    ) {
                        ChildHomeCard("Setting", "",Screen.Subjects.route, navController)
                    }
                }
            }
                Spacer(modifier = Modifier.padding(16.dp))

        }}}}





