package com.example.pfe1.school.schoolClass

import com.example.pfe1.register.data.UserRemote

data class TeachersSchoolClassUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val teachersList : List<UserRemote> = emptyList()
)
