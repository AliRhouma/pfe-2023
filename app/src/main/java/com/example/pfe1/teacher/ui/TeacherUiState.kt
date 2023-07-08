package com.example.pfe1.teacher.ui

import com.example.pfe1.classes.model.Classes
import com.example.pfe1.register.data.UserRemote

data class TeacherUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val teacher : UserRemote = UserRemote(),
    val empty : Boolean = true,

)