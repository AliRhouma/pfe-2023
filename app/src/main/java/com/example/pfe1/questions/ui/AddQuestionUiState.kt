package com.example.pfe1.questions.ui

data class AddQuestionUiState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
)
