package com.example.pfe1.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.login.data.LoginRepositoryImpl
import com.example.pfe1.login.domain.LoginRepository
import com.example.pfe1.login.ui.LoginUiState
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun register(name : String, email : String, password : String){
        // Loading
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null,
        )

        viewModelScope.launch {
            try {
                registerRepository.register(name,email, password)
                // Success
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    error = null,
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }

    }

}