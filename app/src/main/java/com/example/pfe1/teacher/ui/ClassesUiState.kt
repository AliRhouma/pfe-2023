package com.example.pfe1.teacher.ui

import com.example.pfe1.classes.model.Classes
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.task.domain.model.Task

data class ClassesUiState(
    val errorMessage : String? = null,
    val isLoading : Boolean = false,
    val empty : Boolean = true,
    val classes : List<Classes> = emptyList()

)
