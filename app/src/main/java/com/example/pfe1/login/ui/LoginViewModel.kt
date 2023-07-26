package com.example.pfe1.login.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.enumClass.UserType
import com.example.pfe1.idGenerator.IdGeneratorRepositoryImpl
import com.example.pfe1.idGenerator.IdgeneretorRepository
import com.example.pfe1.kidsView.ui.ChildEvents
import com.example.pfe1.kidsView.ui.ChildUiState
import com.example.pfe1.login.data.LoginRepositoryImpl
import com.example.pfe1.login.domain.LoginRepository
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.subjects.data.SubjectRepositoryImpl
import com.example.pfe1.subjects.ui.SubjectsEvent
import com.example.pfe1.subjects.ui.SubjectsUiState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.InvalidClassException

class LoginViewModel : ViewModel() {
    private val loginRepository: LoginRepository = LoginRepositoryImpl()
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

init {
    Firebase.auth.currentUser?.let { getUserType(it.uid) }

}
    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.Login -> login(
                email = event.email,
                password = event.password
            )

            is LoginEvents.getUserType-> getUserType(id = event.email)
            else -> {}
        }
    }
   private fun login(email: String, password: String) {
        // Loading
        _uiState.value = LoginUiState(
            isLoading = true,
            error = null,
        )

        viewModelScope.launch {
            try {
                loginRepository.login(email, password)
                _uiState.value = LoginUiState(
                    isLoading = false,
                    error = null,
                    isSuccess = true
                )
                registerRepository.getUser(email).collect { user ->
                    _uiState.value = LoginUiState(
                        isLoading = false,
                        isSuccess = true,
                        error = null,
                        userType = user.userType
                    )
                    println("kkkkkkkkkkkkk ${user.userType}")
                }
                // Success

            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _uiState.value = LoginUiState(
                    isLoading = false,
                    error = e.message,
                )
            }
        }

    }

    private  fun getUserType(id: String){
        // Loading
        _uiState.value = LoginUiState(
            isLoading = true,
            error = null,
        )

        viewModelScope.launch {
            try {
                println("vmvmvmvmvmv ${Firebase.auth.currentUser?.uid}")
                registerRepository.getUser(id).collect { user ->
                        _uiState.value = LoginUiState(
                            isLoading = false,
                            isSuccess = true,
                            error = null,
                            userType = user.userType
                        )

                    }
            } catch (e: Exception) {
                e.printStackTrace()

                //Failure
                _uiState.value = LoginUiState(
                    isLoading = false,
                    error = e.message,
                )
            }
        }
        //return userType
    }
}