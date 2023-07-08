package com.example.pfe1.task.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.kidsView.ui.ChildUiState
import com.example.pfe1.subjects.domain.Subject
import com.example.pfe1.subjects.ui.AddSubjectUiState
import com.example.pfe1.task.data.TaskRepositoryImpl
import com.example.pfe1.task.domain.TaskRepository
import com.example.pfe1.task.domain.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.UUID

class TasksViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val tasksRepository: TaskRepository = TaskRepositoryImpl()

    val childId = savedStateHandle.get<String>("childId") ?: ""
    val subjectId = savedStateHandle.get<String>("subjectId") ?: ""

    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTasks(childId, subjectId)
       // addTask("Task A", SchoolYear.GRADE_1, Subjects.MATHS, 1)
        /*for (i in 1..6){
            addTask("Task A", SchoolYear.valueOf("GRADE_$i"), Subjects.MATHS, 1)
            addTask("Task A", SchoolYear.valueOf("GRADE_$i"), Subjects.ENGLISH, 1)
            addTask("Task B", SchoolYear.valueOf("GRADE_$i"), Subjects.MATHS, 2)
            addTask("Task B", SchoolYear.valueOf("GRADE_$i"), Subjects.ENGLISH, 2)
            addTask("Task C", SchoolYear.valueOf("GRADE_$i"), Subjects.MATHS, 3)
            addTask("Task C", SchoolYear.valueOf("GRADE_$i"), Subjects.ENGLISH, 3)
            addTask("Task D", SchoolYear.valueOf("GRADE_$i"), Subjects.MATHS, 4)
            addTask("Task D", SchoolYear.valueOf("GRADE_$i"), Subjects.ENGLISH, 4)
        }*/

    }

    fun onEvent(event: TaskEvents) {
        when (event) {
            is TaskEvents.GetTasks -> getTasks(childId, subjectId)
        }
    }

    fun getTasks(childId: String, subjectId: String) {
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                // Success

                tasksRepository.getTasks(childId, subjectId).collect { tasks ->
                    if (tasks.isNotEmpty()){
                    _uiState.value = TasksUiState(tasks = tasks)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false
                    )

                }}
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _uiState.value = TasksUiState(
                    errorMessage = e.message
                )
            }
        }
    }

    private fun addTask(name: String, schoolYear: SchoolYear, subject: Subjects, taskNum: Int) {

        val task = Task(
            id = UUID.randomUUID().toString(),
            name = name,
            schoolYear = schoolYear,
            subjects = subject,
            taskDescription = "",
            taskNum = taskNum,
        )

        viewModelScope.launch {
            try {
                tasksRepository.addTask(task)

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }
}
