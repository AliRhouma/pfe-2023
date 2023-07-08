package com.example.pfe1.school.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.childHome.ui.ChildHomeUiState
import com.example.pfe1.classes.domain.ClassesReositoryImpl
import com.example.pfe1.classes.model.Classes
import com.example.pfe1.classes.model.ClassesRepository
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.example.pfe1.kidsView.ui.AddChildUiState
import com.example.pfe1.kidsView.ui.ChildUiState
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.subjects.ui.SubjectsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.UUID

class SchoolViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val schoolRepository: ClassesRepository = ClassesReositoryImpl()
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()
    private val childRepository: ChildRepository = ChildRepositoryImpl()


    private val _schoolUiState = MutableStateFlow(SchoolUiState())
    val schoolUiState = _schoolUiState.asStateFlow()

    private val _addClassUiState = MutableStateFlow(AddClassUiState())
    val addClassUiState = _addClassUiState.asStateFlow()

    private val _classesUiState = MutableStateFlow(ClassesUiStates())
    val classesUiState = _classesUiState.asStateFlow()

    private val _childUiState = MutableStateFlow(ChildUiState())
    val childUiState = _childUiState.asStateFlow()

    val schoolId = savedStateHandle.get<String>("schoolId") ?: ""

    init {
        println("schoooolId6666 ${schoolId}")
        getSchoolName(schoolId)
        getClassesBySchoolId(schoolId)
    }



    fun onEvent(event: SchoolEvents) {
        when (event) {
            is SchoolEvents.GetClasses -> getClassesBySchoolId(
                id = event.schoolId
            )

            is SchoolEvents.AddClasses -> addClass(
                name = event.name,
                schoolId = event.schoolId,
                grade = event.grade,
            )

            is SchoolEvents.GetSchoolName -> getSchoolName(
                email = event.schoolId
            )
        }
    }

    private fun getClassesBySchoolId(id: String) {
        _classesUiState.value = _classesUiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                schoolRepository.getClassesBySchoolId(id).collect { classes ->
                    _classesUiState.value = _classesUiState.value.copy(
                        isLoading = false,
                        classesList = classes,
                        empty = false,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _classesUiState.value = _classesUiState.value.copy(
                    isFailure = true,

                )
            }
        }
    }

    private fun addClass(name: String, schoolId: String, grade: String) {
        _addClassUiState.value = AddClassUiState(
            isLoading = true,
        )

        val classes = Classes(
            id = UUID.randomUUID().toString(),
            name = name,
            schoolId = schoolId,
            grade = grade
        )

        viewModelScope.launch {
            try {
                schoolRepository.addClass(classes)
                _addClassUiState.value = AddClassUiState(
                    isSuccess = true
                )
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                 _addClassUiState.value = AddClassUiState(
                   errorMessage = e.message
                 )
            }
        }
    }

    private fun getSchoolName(email: String) {
        _schoolUiState.value = _schoolUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                  registerRepository.getUser(schoolId).collect() { school ->
                    _schoolUiState.value = SchoolUiState(
                        isLoading = false,
                        schoolName = school.name,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _schoolUiState.value = _schoolUiState.value.copy(
                    isFailure = true
                )
            }
        }
    }

    private fun getStudentsBySchoolId(schoolId: String) {
        viewModelScope.launch {
            try {
                childRepository.getChildsByClassId(schoolId).collect { childs ->
                    _childUiState.value = ChildUiState(
                        childList = childs
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}