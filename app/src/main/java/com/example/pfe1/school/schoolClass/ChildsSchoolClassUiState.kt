package com.example.pfe1.school.schoolClass

import com.example.pfe1.kidsView.domain.model.Child

data class ChildsSchoolClassUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val studentsList : List<Child> = emptyList()
)
