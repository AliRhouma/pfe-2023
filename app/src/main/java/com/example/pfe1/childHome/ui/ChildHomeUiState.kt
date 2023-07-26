package com.example.pfe1.childHome.ui

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.domain.model.Child

data class ChildHomeUiState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val child: Child = Child("","","",SchoolYear.DEFAULT,"","4pNf4BT913VDJkOlbQ4bFHgYgwI3","33d2b161-4d02-4762-8fad-143773287c26","")
)

