package com.example.pfe1.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.login.data.LoginRepositoryImpl
import com.example.pfe1.login.domain.LoginRepository
import com.example.pfe1.subjects.data.SubjectRepositoryImpl
import com.example.pfe1.subjects.ui.SubjectsEvent
import com.example.pfe1.subjects.ui.SubjectsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.InvalidClassException

class LoginViewModel : ViewModel() {
    private val loginRepository: LoginRepository = LoginRepositoryImpl()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun login(email : String, password : String){
        // Loading
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null,
        )

        viewModelScope.launch {
            try {
                loginRepository.login(email, password)
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