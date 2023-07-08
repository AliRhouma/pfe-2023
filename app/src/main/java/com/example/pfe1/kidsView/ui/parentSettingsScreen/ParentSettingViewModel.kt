package com.example.pfe1.kidsView.ui.parentSettingsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.school.schoolSzttings.SchoolSettingEvents
import com.example.pfe1.school.schoolSzttings.SchoolSettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ParentSettingViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val _parentSettingsUiState = MutableStateFlow(ParentSettingsUiState())
    val parentSettingsUiState = _parentSettingsUiState.asStateFlow()

    val parentId = savedStateHandle.get<String>("parentId") ?: ""

    init {

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

    }
    private fun updateParentSettings(parentId: String, name: String) {}
    private fun logOut() {}





}
