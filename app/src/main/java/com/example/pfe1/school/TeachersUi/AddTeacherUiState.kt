package com.example.pfe1.school.TeachersUi

import com.example.pfe1.register.data.UserRemote

data class AddTeachersUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val isSuccess : Boolean = false
)

