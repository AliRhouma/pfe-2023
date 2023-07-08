package com.example.pfe1.school.ui

import com.example.pfe1.classes.model.Classes

data class ClassesUiStates(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val empty : Boolean = true,
    val classesList : List<Classes> = emptyList()
)
