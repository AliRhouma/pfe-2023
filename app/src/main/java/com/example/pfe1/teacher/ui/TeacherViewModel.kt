package com.example.pfe1.teacher.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.classes.domain.ClassesReositoryImpl
import com.example.pfe1.classes.model.ClassesRepository
import com.example.pfe1.kidsView.ui.AddChildUiState
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.data.UserRemote
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.school.ui.SchoolEvents
import com.example.pfe1.school.ui.SchoolUiState
import com.example.pfe1.teacher.data.TeacherRepositoryImpl
import com.example.pfe1.teacher.domain.TeacherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class TeacherViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()
    private val teacherRepository: TeacherRepository = TeacherRepositoryImpl()
    private val classesRepository: ClassesRepository = ClassesReositoryImpl()

    private val _teacherUiState = MutableStateFlow(TeacherUiState())
    val teacherUiState = _teacherUiState.asStateFlow()

    private val _classesUistate = MutableStateFlow(ClassesUiState())
    val classesUiState = _classesUistate.asStateFlow()

    val teacherId = savedStateHandle.get<String>("teacherId") ?: ""

    init {
        println("t3adat")
        getTeacher(teacherId = teacherId)
        }
    fun onEvent(event: TeacherEvents) {
        when (event) {
            is TeacherEvents.getClasses -> getAllClasses(
                teacherId = event.teacherid
            )
            is TeacherEvents.JoinScchool -> joinSchool(
                schoolId = event.schoolId,
            )
            is TeacherEvents.GetTeacherData -> getTeacher(teacherId)

        }}
    var response = UserRemote()

    fun getTeacher(teacherId: String){
        _teacherUiState.value = _teacherUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                registerRepository.getUser(teacherId).collect() {
                    _teacherUiState.value = _teacherUiState.value.copy(
                        teacher = it
                    )
                }
    } catch (e: Exception) {
            e.printStackTrace()

            // Failure

        }
    }
}
    fun joinSchool(schoolId: String,) {
        _teacherUiState.value = TeacherUiState(
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
                        schoolId = response.schoolId,
                        classId = response.classId,
                        schoolIdList = response.schoolIdList+schoolId,
                        classIdList = response.classIdList
                    )
                }
                registerRepository.updateUser(response)
                response = UserRemote()
                //registerRepository.updateUser(schoolId,techerId)

            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _teacherUiState.value = TeacherUiState(
                    isFailure = false
                )
            }

    }}

    private  fun getAllClasses(teacherId: String) {
        _classesUistate.value = ClassesUiState(
            isLoading = true
        )
        viewModelScope.launch {

        try {
            classesRepository.getClassesByTeacherId(teacherId).collect { classes ->
                _classesUistate.value = _classesUistate.value.copy(
                    isLoading = false,
                    classes = classes,
                    empty = false,
                )
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()

            // Failure
            _classesUistate.value = _classesUistate.value.copy(


                )

    }}}
    private fun getClasses(teacherId: String) {}







}