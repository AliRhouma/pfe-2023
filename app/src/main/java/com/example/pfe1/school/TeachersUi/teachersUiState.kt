package com.example.pfe1.school.TeachersUi

import com.example.pfe1.classes.model.Classes
import com.example.pfe1.register.data.UserRemote

data class TeachersUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val teachersList : List<UserRemote> = emptyList()
)
