package com.example.pfe1.login.ui

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.ui.ChildEvents

sealed class LoginEvents{
    data class getUserType(
        val email: String,
    ): LoginEvents()

    data class Login(
        val email: String,
        val password: String
    ): LoginEvents()

}

