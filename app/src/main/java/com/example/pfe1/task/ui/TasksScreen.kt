package com.example.pfe1.task.ui

import android.os.StatFs
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pfe1.kidsView.ui.components.ChildCard

@Composable
fun TasksScreen(navController: NavController, childId: String, subjectId: String ) {
    val viewModel = viewModel<TasksViewModel>()
    val state by viewModel.uiState.collectAsState()


    Scaffold() { paddingValues ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            if (state.isLoading) {
                item { CircularProgressIndicator() }
            } else if (state.errorMessage != null) {
                //
            } else {
                items(state.tasks) { task ->
                    TaskCard(navController, task, childId, subjectId)
                }

            }
        }
    }
}