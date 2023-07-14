package com.example.pfe1.kidsView.ui.parentSettingsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ParentSettingViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val _parentSettingsUiState = MutableStateFlow(ParentSettingsUiState())
    val parentSettingsUiState = _parentSettingsUiState.asStateFlow()

    val parentId = savedStateHandle.get<String>("parentId") ?: ""

    init {
        getParentSettings(parentId = parentId)
    }

    fun onEvent(event: ParentSettingEvents) {
        when (event) {
            is ParentSettingEvents.GetParentData -> getParentSettings(
                parentId = parentId,
            )
            is ParentSettingEvents.UpdateParentData -> updateParentSettings(
                parentId = parentId,
                name = event.parentName
            )
            is ParentSettingEvents.Logout -> logOut()
        }
    }

    private fun getParentSettings(parentId: String) {
        _parentSettingsUiState.value = _parentSettingsUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                registerRepository.getUser(parentId).collect() { parent ->
                    _parentSettingsUiState.value = ParentSettingsUiState(
                        isLoading = false,
                        parent = parent,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _parentSettingsUiState.value = _parentSettingsUiState.value.copy(
                    isFailure = true
                )
            }
        }
    }

    private fun updateParentSettings(parentId: String, name: String) {}
    private fun logOut() {}





}
