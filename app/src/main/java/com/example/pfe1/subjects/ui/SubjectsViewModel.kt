package com.example.pfe1.subjects.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.subjects.data.SubjectRepositoryImpl
import com.example.pfe1.subjects.domain.Subject
import com.example.pfe1.subjects.domain.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SubjectsViewModel : ViewModel() {
    private val subjectRepository: SubjectRepository = SubjectRepositoryImpl()

    private val _uiState = MutableStateFlow(SubjectsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSubjects()
    }

    fun onEvent(event: SubjectsEvent){
        when(event) {
            is SubjectsEvent.Event1 -> getSubjects()
            SubjectsEvent.GetSubjects -> getSubjects()
        }
    }

    private fun getSubjects() {
        // Loading
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )

        try {
            viewModelScope.launch {
                val subjects =  subjectRepository.getAllSubject()
                // Success
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    subjects = subjects
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()

            // Failure
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isFailure = true
            )
        }
    }

}

fun main() {

    val flow = flow {
        var x = 0
        while (true) {
            emit(x)
            x++
            kotlinx.coroutines.delay(100)
        }
    }

    runBlocking {
        flow.collect {
            println(it)
        }
    }

}

