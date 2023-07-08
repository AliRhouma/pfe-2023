package com.example.pfe1.classes.ui

import com.example.pfe1.register.data.UserRemote

data class TeacherUiState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val teacherList: List<UserRemote> = emptyList()
)
