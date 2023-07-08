package com.example.pfe1.school.schoolSzttings

import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.register.data.UserRemote

data class SchoolSettingsUiState(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val school : UserRemote = UserRemote()
)
