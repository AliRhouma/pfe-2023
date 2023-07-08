package com.example.pfe1.kidsView.ui

import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.register.data.UserRemote

data class ParentNameUiState(
    val isFailure: Boolean = false,
    val isLoading: Boolean = false,
    val parentName: String = ""
)
