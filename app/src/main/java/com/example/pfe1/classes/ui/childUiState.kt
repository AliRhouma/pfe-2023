package com.example.pfe1.classes.ui

import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.navigation.Screen

data class childUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val subjects : List<Child> = emptyList()
)
