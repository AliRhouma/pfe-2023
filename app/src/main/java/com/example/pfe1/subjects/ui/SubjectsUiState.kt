package com.example.pfe1.subjects.ui

import com.example.pfe1.subjects.domain.Subject

data class SubjectsUiState(

    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val subjects : List<Subject> = emptyList()
)
