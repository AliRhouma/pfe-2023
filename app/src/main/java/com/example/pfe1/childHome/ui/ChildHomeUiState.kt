package com.example.pfe1.childHome.ui

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.domain.model.Child

data class ChildHomeUiState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val child: Child = Child("","","",SchoolYear.DEFAULT,"")
)

