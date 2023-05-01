package com.example.pfe1.kidsView.ui

import com.example.pfe1.kidsView.domain.model.Child

data class AddChildUiState(
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
)
