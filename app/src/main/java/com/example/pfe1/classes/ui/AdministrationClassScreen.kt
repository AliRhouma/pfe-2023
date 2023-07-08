    package com.example.pfe1.classes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AdministrationClassScreen(navController: NavController, classesId: String) {
    val viewModel = viewModel<ClassesViewModel>()
    val state by viewModel.childUiState.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        state.childList.map { childs -> 
            Text(text = childs.name)
        }
    }
}