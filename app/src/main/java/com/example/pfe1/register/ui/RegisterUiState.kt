package com.example.pfe1.register.ui

data class RegisterUiState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val isSuccess : Boolean = false
)
