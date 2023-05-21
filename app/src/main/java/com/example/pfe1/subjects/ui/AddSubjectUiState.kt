package com.example.pfe1.subjects.ui

data class AddSubjectUiState(
    val isLoading : Boolean = false,
    val errorMessage: String? = "",
    val isSuccess: Boolean = false
)
