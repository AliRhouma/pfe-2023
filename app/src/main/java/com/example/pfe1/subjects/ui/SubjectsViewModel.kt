package com.example.pfe1.subjects.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.subjects.data.SubjectRepositoryImpl
import com.example.pfe1.subjects.domain.Subject
import com.example.pfe1.subjects.domain.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class SubjectsViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

 val childId = savedStateHandle.get<String>("childId")?:""

    private val subjectRepository: SubjectRepository = SubjectRepositoryImpl()

    private val _uiState = MutableStateFlow(SubjectsUiState())
    val uiState = _uiState.asStateFlow()

    private  val _addSubjectUiState = MutableStateFlow(AddSubjectUiState())
    val addSubjectUiState = _addSubjectUiState.asStateFlow()



    init {
        getSubjects()
        /*for (grade in 1..6) {
            addSubject("Math", SchoolYear.valueOf("GRADE_$grade"), Subjects.MATHS)
            addSubject("English", SchoolYear.valueOf("GRADE_$grade"), Subjects.ENGLISH)
            if (grade > 2){
            addSubject("Science", SchoolYear.valueOf("GRADE_$grade"), Subjects.SCIENCE)}
            if (grade > 4){
            addSubject("History", SchoolYear.valueOf("GRADE_$grade"), Subjects.HISTORY)
            addSubject("Geography", SchoolYear.valueOf("GRADE_$grade"), Subjects.GEOGRAPHY)}
        }*/ }

    fun onEvent(event: SubjectsEvent){
        when (event) {
            is SubjectsEvent.GetSubjects -> getSubjects()
            is SubjectsEvent.AddSubject -> addSubject(
                name = event.name,
                schoolYear = event.schoolYear,
                type = event.type
            )
        }
    }

    private fun addSubject(name: String, schoolYear: SchoolYear, type: Subjects) {
        _addSubjectUiState.value = AddSubjectUiState(isLoading = true)

        val subject = Subject(
            id = UUID.randomUUID().toString(),
            name = name,
            type = type,
            schoolYear = schoolYear,
        )

        viewModelScope.launch {
            try {
                subjectRepository.addSubject(subject)
                _addSubjectUiState.value = AddSubjectUiState(
                    isLoading = false,
                    isSuccess = true
                )
            } catch (e: Exception) {
            e.printStackTrace()

            // Failure
            _addSubjectUiState.value = AddSubjectUiState(
                errorMessage = e.message
            )
            }
        }
    }

    private fun getSubjects() {
        // Loading
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                subjectRepository.getSubjects(childId).collect { subjects ->
                    _uiState.value = SubjectsUiState(
                        subjects = subjects
                    )
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }

            catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isFailure = true
                )
            }
        }

    }

}


