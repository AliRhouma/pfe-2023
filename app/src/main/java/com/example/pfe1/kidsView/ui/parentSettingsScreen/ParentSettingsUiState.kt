package com.example.pfe1.kidsView.ui.parentSettingsScreen

import com.example.pfe1.register.data.UserRemote

data class ParentSettingsUiState(
    val isFailure: Boolean = false,
    val isLoading: Boolean = false,
    val parent: UserRemote = UserRemote()
)
