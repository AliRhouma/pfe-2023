package com.example.pfe1.login.ui

data class LoginUiState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val isSuccess : Boolean = false
)