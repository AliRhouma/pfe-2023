package com.example.pfe1.school.StudentsUi

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val childRepository: ChildRepository = ChildRepositoryImpl()

    private val _studentsUiState = MutableStateFlow(StudentsUiState())
    val studentsUiState = _studentsUiState.asStateFlow()

    private val _addStudentsUiState = MutableStateFlow(AddStudentsUiState())
    val addStudentsUiState = _addStudentsUiState.asStateFlow()

    val schoolId = savedStateHandle.get<String>("schoolId") ?: ""

    init {
        getStudentsBySchoolId(schoolId)
    }
    fun onEvent(event: StudentsEvents) {
        when (event) {
            is StudentsEvents.GetStudents-> getStudentsBySchoolId(
                id = event.schoolId
            )

            is StudentsEvents.AddStudents -> addStudentToSchool(
                StudentId = event.StudentId,
                schoolId = schoolId
            )
            else -> {}
        }
    }
    private fun getStudentsBySchoolId(id: String){
        _studentsUiState.value = _studentsUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
               childRepository.getChildsBySchoolId(id).collect { Students ->
                    _studentsUiState.value = _studentsUiState.value.copy(
                        isLoading = false,
                        studentsList = Students,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _studentsUiState.value = _studentsUiState.value.copy(
                    isFailure = true,

                    )
            }
        }
    }
    var response = Child(
        id = "",
        parentId = "",
        name = "",
        schoolYear = SchoolYear.DEFAULT,
        schoolId = "",
        imageUrl = "",
        classId = "",
        studentId= ""
    )

    private fun addStudentToSchool(StudentId: String, schoolId: String) {
        _studentsUiState.value = _studentsUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {

                childRepository.getChildByStudentId(StudentId).collect() {
                    response = it
                println("aaaaaaaaaaa ${response.id}" )
                    response = Child(
                        id = response.id,
                        parentId = response.parentId,
                        name = response.name,
                        schoolYear = response.schoolYear,
                        imageUrl = response.imageUrl,
                        schoolId = schoolId,
                        classId = "",
                        studentId = response.studentId
                    )

                childRepository.updateChild(response)
                _addStudentsUiState.value = AddStudentsUiState(
                    isLoading = false,
                )}
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _addStudentsUiState.value = AddStudentsUiState(
                    isFailure = true
                )
            }
        }


    }


}