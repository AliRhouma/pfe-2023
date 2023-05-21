package com.example.pfe1.questions.ui

import com.example.pfe1.questions.domain.model.Question

data class QuestionUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val questions : List<Question> = emptyList()
)
