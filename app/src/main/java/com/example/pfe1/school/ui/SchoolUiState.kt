package com.example.pfe1.school.ui

import com.example.pfe1.register.domain.User

data class SchoolUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val schoolName : String = ""
)