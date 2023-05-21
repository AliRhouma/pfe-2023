package com.example.pfe1.task.ui

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.task.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TasksUiState(
    val errorMessage : String? = null,
    val isLoading : Boolean = false,
    val tasks : List<Task> = listOf(Task("","",SchoolYear.GRADE_1,Subjects.ENGLISH,"",3))
)
