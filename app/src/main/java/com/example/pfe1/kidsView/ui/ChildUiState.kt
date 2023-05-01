package com.example.pfe1.kidsView.ui

import com.example.pfe1.kidsView.domain.model.Child

data class ChildUiState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val childList: List<Child> = emptyList()
)
