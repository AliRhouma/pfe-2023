package com.example.pfe1.school.schoolClass

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.example.pfe1.kidsView.ui.ChildUiState
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.data.UserRemote
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.school.StudentsUi.AddStudentsUiState
import com.example.pfe1.school.TeachersUi.AddTeachersUiState
import com.example.pfe1.school.ui.SchoolEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class SchoolClassViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()
    private val childRepository: ChildRepository = ChildRepositoryImpl()

    private val _childsSchoolUiState = MutableStateFlow(ChildsSchoolClassUiState())
    val childsSchoolUiState = _childsSchoolUiState.asStateFlow()

    private val _studentsUiState = MutableStateFlow(AllChildsSchoolClassUiState())
    val AllChildsSchoolUiState = _studentsUiState.asStateFlow()

    private val _teachersSchoolUiState = MutableStateFlow(TeachersSchoolClassUiState())
    val teachersSchoolUiState = _teachersSchoolUiState.asStateFlow()


    val classId = savedStateHandle.get<String>("classId") ?: ""
    val schoolId = savedStateHandle.get<String>("schoolId") ?: ""

    var response1 = Child(
        id = "",
        parentId = "",
        name = "",
        schoolYear = SchoolYear.DEFAULT,
        schoolId = "",
        imageUrl = "",
        classId = "",
        studentId = "",
    )

    init {
        getAllStudents(schoolId)
        getChildsByClassId(classId)
        println("ooooooooooooooooooooooooo ${classId}")
        getTeachersByClassId(classId)
    }

    fun onEvent(event: SchoolClassEvents) {
        when (event) {
            is SchoolClassEvents.GetChildsByClassId -> getChildsByClassId(
                classId
            )
            is SchoolClassEvents.GetTeachersByClassId -> getTeachersByClassId(
                classId
            )
            is SchoolClassEvents.DeleteChild -> deleteChild(
                event.childId
            )
            is SchoolClassEvents.MoveChild -> moveChild(
                event.childId,event.classIdDestination
            )
            is SchoolClassEvents.AddChild -> addChild(
                event.childId
            )
            is SchoolClassEvents.DeleteTeacher -> deleteTeacher(
                event.teacherId
            )
            is SchoolClassEvents.AddTeacher -> addTeacher(
                event.TeacherId
            )
            is SchoolClassEvents.GetTeachersBySchoolId -> getTeachersBySchoolId(
                schoolId
            )
            is SchoolClassEvents.GetChildsBySchoolId -> getStudentsBySchoolId(
                schoolId
            )
            is SchoolClassEvents.GetAllStudents -> getAllStudents(
                id = schoolId
            )

            else -> {}
        }
    }

    private fun getAllStudents(id: String){
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
    private fun getChildsByClassId(classId : String){
        _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                childRepository.getChildsByClassId(classId).collect { Students ->
                    _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
                        isLoading = false,
                        studentsList = Students,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
                    isFailure = true,

                    )
            }
        }
    }


    private fun getTeachersByClassId(classId : String){
        _teachersSchoolUiState.value = _teachersSchoolUiState.value.copy(
            isLoading = true
        )
        println("ooooooooooooooooooooooooo is loading ${classId}")

        viewModelScope.launch {
            println("ooooooooooooooooooooooooo  launch ${classId}")
            try {
                println("ooooooooooooooooooooooooo try ${classId}")
                registerRepository.getTeachersByClassId(classId).collect { teachers ->
                    _teachersSchoolUiState.value = _teachersSchoolUiState.value.copy(
                        isLoading = false,
                        teachersList = teachers,

                    )
                    println("ooooooooooooooooooooooooo ${teachers.isEmpty()}")
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _teachersSchoolUiState.value = _teachersSchoolUiState.value.copy(
                    isFailure = true,

                    )
            }
        }
    }

    private fun deleteTeacher(teacherId : String){}
    private fun deleteChild(childId : String){
        _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
            isLoading = true
        )
        var updatedState = true

        viewModelScope.launch {
            try {

                childRepository.getChild(childId).collect() {
                    if (updatedState) {
                    response1 = it
                    println("aaaaaaaafffaaa ${response.classId}" )
                    response1 = Child(
                        id = response1.id,
                        parentId = response1.parentId,
                        name = response1.name,
                        schoolYear = response1.schoolYear,
                        imageUrl = response1.imageUrl,
                        schoolId = response1.schoolId!!,
                        classId = "",
                        studentId = response1.studentId
                    )

                    childRepository.updateChild(response1)
                    updatedState = false}

                }
                response1 = Child(
                    id = "",
                    parentId = "",
                    name = "",
                    schoolYear = SchoolYear.DEFAULT,
                    schoolId = "",
                    imageUrl = "",
                    classId = "",
                    studentId = ""
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure

            }
        }}

    private fun moveChild(childId : String, classIdDestination: String){
        _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {

                childRepository.getChild(childId).collect() {

                    response1 = it
                    println("aaaaaaaaaaa ${response.id}" )
                    response1 = Child(
                        id = response1.id,
                        parentId = response1.parentId,
                        name = response1.name,
                        schoolYear = response1.schoolYear,
                        imageUrl = response1.imageUrl,
                        schoolId = "",
                        classId = classIdDestination,
                        studentId = response1.studentId
                    )

                    childRepository.updateChild(response1)

                }
                response1 = Child(
                    id = "",
                    parentId = "",
                    name = "",
                    schoolYear = SchoolYear.DEFAULT,
                    schoolId = "",
                    imageUrl = "",
                    classId = "",
                    studentId = ""
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure

            }
        }

    }

    var response = UserRemote()

    private fun addTeacher(teacherId : String){
        _teachersSchoolUiState.value = _teachersSchoolUiState.value.copy(
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
                        classId = classId,
                        schoolIdList = response.schoolIdList,
                        classIdList = response.classIdList+classId
                    )
                }
                registerRepository.updateUser(response)
                response = UserRemote()
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure

            }
        }
    }


    private fun addChild(childId : String){
            _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
                isLoading = true
            )
        var updatedState = true
            viewModelScope.launch {
                try {



                    childRepository.getChild(childId).collect() {
                        if (updatedState) {

                            response1 = it
                            println("aaaaaaaaaaa ${response.id}")
                            response1 = Child(
                                id = response1.id,
                                parentId = response1.parentId,
                                name = response1.name,
                                schoolYear = response1.schoolYear,
                                imageUrl = response1.imageUrl,
                                schoolId = response1.schoolId,
                                classId = classId,
                                studentId = response1.studentId
                            )

                            println("yeeeeeeeeeeees")
                            childRepository.updateChild(response1)
                            updatedState = false

                        }

                    }
                } catch (e: Exception) {
                    e.printStackTrace()

                    // Failure

                }
            }}


    private fun getStudentsBySchoolId(id: String){
        _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                childRepository.getChildsBySchoolId(id).collect { Students ->
                    _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
                        isLoading = false,
                        studentsList = Students,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _childsSchoolUiState.value = _childsSchoolUiState.value.copy(
                    isFailure = true,

                    )
            }
        }
    }

    private fun getTeachersBySchoolId(id: String){
        _teachersSchoolUiState.value = _teachersSchoolUiState.value.copy(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                println("ooooooooooooooooooooooooo ${classId}")
                registerRepository.getTeachersByClassId(id).collect { teachers ->
                    _teachersSchoolUiState.value = _teachersSchoolUiState.value.copy(
                        isLoading = false,
                        teachersList = teachers,
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _teachersSchoolUiState.value = _teachersSchoolUiState.value.copy(
                    isFailure = true,

                    )
            }
        }
    }








}