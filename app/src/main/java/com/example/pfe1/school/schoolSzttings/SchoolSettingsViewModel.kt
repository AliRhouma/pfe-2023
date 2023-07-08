package com.example.pfe1.school.schoolSzttings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.data.UserRemote
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.school.ui.SchoolEvents
import com.example.pfe1.school.ui.SchoolUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class SchoolSettingsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val _schoolSettingsUiState = MutableStateFlow(SchoolSettingsUiState())
    val schoolSettingsUiState = _schoolSettingsUiState.asStateFlow()

    val schoolId = savedStateHandle.get<String>("schoolId") ?: ""



    init {
        getSchoolData(schoolId)
    }

    fun onEvent(event: SchoolSettingEvents) {
        when (event) {
            is SchoolSettingEvents.GetSchoolData -> getSchoolData(
                schoolId = schoolId
            )
            is SchoolSettingEvents.UpdateSchoolData -> updateSchoolSettings(schoolId,event.schoolName)
        }
    }

   var response = UserRemote()

    private fun updateSchoolSettings(schoolId: String, schoolName: String) {
        var updatedState = true

        viewModelScope.launch {
            try {

                registerRepository.getUser(schoolId).collect() {
                    if (updatedState) {
                        response = it
                        println("aaaaaaaafffaaa ${response.classId}")
                        response = UserRemote(
                            id = response.id,
                            name = schoolName,
                            email = response.email,
                            userType = response.userType,
                            schoolId = response.schoolId,
                            classId = response.classId,
                            schoolIdList = response.schoolIdList,
                            classIdList = response.classIdList
                        )


                        updatedState = false
                    }
                    registerRepository.updateUser(response)

                }
                response = UserRemote(
                    id = response.id,
                    name = response.name,
                    email = response.email,
                    userType = response.userType,
                    schoolId = response.schoolId,
                    classId = response.classId,
                    schoolIdList = response.schoolIdList,
                    classIdList = response.classIdList
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure

            }
        }
    }
    private fun getSchoolData(schoolId: String) {
        _schoolSettingsUiState.value = _schoolSettingsUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                registerRepository.getUser(schoolId).collect() { school ->
                    _schoolSettingsUiState.value = SchoolSettingsUiState(
                        isLoading = false,
                        school = school,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _schoolSettingsUiState.value = _schoolSettingsUiState.value.copy(
                    isFailure = true
                )
            }
        }
    }
}