package com.example.pfe1.school.schoolClass

import com.example.pfe1.kidsView.domain.model.Child

data class AllChildsSchoolClassUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val studentsList : List<Child> = emptyList()
)