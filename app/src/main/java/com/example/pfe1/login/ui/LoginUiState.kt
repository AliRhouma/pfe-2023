package com.example.pfe1.login.ui

import com.example.pfe1.enumClass.UserType

data class LoginUiState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val isSuccess : Boolean = false,
    val userType: UserType = UserType.DEFAULT,
)