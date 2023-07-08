package com.example.pfe1.school.TeachersUi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.data.UserRemote
import com.example.pfe1.register.domain.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class TeachersViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val _teachersUiState = MutableStateFlow(TeachersUiState())
    val teachersUiState = _teachersUiState.asStateFlow()

    private val _addTeachersUiState = MutableStateFlow(AddTeachersUiState())
    val addTeachersUiState = _addTeachersUiState.asStateFlow()

    val schoolId = savedStateHandle.get<String>("schoolId") ?: ""

    init {
        getTeachersBySchoolId(schoolId)
    }
    fun onEvent(event: TeachersEvents) {
        when (event) {
            is TeachersEvents.GetTeachers-> getTeachersBySchoolId(
                id = event.schoolId
            )

            is TeachersEvents.AddTeachers -> addTeacherToSchool(
                teacherId = event.teacherId,
                schoolId = schoolId
            )
            else -> {}
        }
    }
    private fun getTeachersBySchoolId(id: String){
        _teachersUiState.value = _teachersUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
               registerRepository.getTeachersBySchoolId(id).collect { teachers ->
                    _teachersUiState.value = _teachersUiState.value.copy(
                        isLoading = false,
                        teachersList = teachers,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _teachersUiState.value = _teachersUiState.value.copy(
                    isFailure = true,

                    )
            }
        }
    }
    var response = UserRemote()

    private fun addTeacherToSchool(teacherId: String, schoolId: String) {
        _teachersUiState.value = _teachersUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {

                registerRepository.getUser(teacherId).collect() {
                    response = it
                println("aaaaaaaaaaa ${response.id}" )
                    response = UserRemote(
                        id = response.id,
                        name = response.name,
                        email = response.email,
                        userType = response.userType,
                        schoolId = schoolId,
                        classId = response.classId,
                        schoolIdList = response.schoolIdList+schoolId,
                        classIdList = response.classIdList
                    )}
                registerRepository.updateUser(response)
                _addTeachersUiState.value = AddTeachersUiState(
                    isLoading = false,
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _addTeachersUiState.value = AddTeachersUiState(
                    isFailure = true
                )
            }
        }


    }


}