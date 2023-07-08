package com.example.pfe1.classes.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.example.pfe1.kidsView.ui.ChildUiState
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ClassesViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val childRepository: ChildRepository = ChildRepositoryImpl()
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val _childUiState = MutableStateFlow(ChildUiState())
    val childUiState = _childUiState.asStateFlow()

    private val _teacherUiState = MutableStateFlow(TeacherUiState())
    val teacherUiState = _teacherUiState.asStateFlow()

    val classId = savedStateHandle.get<String>("classId") ?: ""

    init {
        getStudents(classId)
        getTeacher(classId)
    }

    fun onEvent(event: ClassEvents) {
        when (event) {
            is ClassEvents.GetStudents -> getStudents(
                classesId = event.classesId
            )
            is ClassEvents.GetTeachers -> getTeacher(
                classesId = event.classesId
            )
            is ClassEvents.DeleteStudent -> getStudents(
                classesId = event.studentId
            )
            is ClassEvents.MoveStudent -> getStudents(
                classesId = event.classesDestinationId
            )
            is ClassEvents.AddStudent -> getStudents(
            classesId = event.studentId
            )
            is ClassEvents.AddTeacher -> getStudents(
            classesId = event.teacherId
            )
        }
    }


    private fun getStudents(classesId: String) {
        viewModelScope.launch {
            try {
                childRepository.getChildsByClassId(classesId).collect { childs ->
                    _childUiState.value = ChildUiState(
                        childList = childs
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getTeacher(classesId: String) {
        viewModelScope.launch {
            try {
                registerRepository.getTeachersByClassId(classesId).collect { teachers ->
                    _teacherUiState.value = TeacherUiState(
                        teacherList = teachers
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }











}
