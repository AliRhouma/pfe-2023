package com.example.pfe1.school.StudentsUi

import com.example.pfe1.classes.model.Classes
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.register.data.UserRemote

data class StudentsUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val studentsList : List<Child> = emptyList()
)
