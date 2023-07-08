package com.example.pfe1.questions.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.childHome.ui.ChildHomeEvents
import com.example.pfe1.childHome.ui.ChildHomeUiState
import com.example.pfe1.questions.data.QuestionsRepositoryImpl
import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.questions.domain.repository.QuestionsRepository
import com.example.pfe1.subjects.ui.SubjectsEvent
import com.example.pfe1.task.data.TaskRepositoryImpl
import com.example.pfe1.task.domain.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

class QuestionViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val questionRepository: QuestionsRepository = QuestionsRepositoryImpl()
    private val tasksRepository: TaskRepository = TaskRepositoryImpl()


    private val _uiState = MutableStateFlow(QuestionUiState())
    val uiState = _uiState.asStateFlow()

    private val _addQuestionUiState = MutableStateFlow(AddQuestionUiState())
    val addQuestionUiState = _addQuestionUiState.asStateFlow()


    val childId = savedStateHandle.get<String>("childId") ?: ""
    val taskId = savedStateHandle.get<String>("taskId") ?: ""

    init {
       getQuestions("0173d456-2289-4a54-9226-719990e914b3")
        addQuestionMultipleChoice("1+1+1",3, listOf("12","3","7"))
    }

    fun onEvent(event: QuestionEvent){
        when (event) {
            is QuestionEvent.AddQuestion.TrueFalse -> addQuestionTrueFalse(
                event.text,
                event.correctAnswer
            )
            is QuestionEvent.AddQuestion.MultipleChoice -> addQuestionMultipleChoice(
                event.text,
                event.correctAnswer,
                event.answers
            )
            is QuestionEvent.GetQuestions -> getQuestions(taskId)
        }
    }

    private fun getQuestions(taskId: String) {
        _uiState.value = QuestionUiState(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                questionRepository.getAllQuestions(taskId).collect { questions ->
                    _uiState.value = QuestionUiState(
                        isLoading = false,
                        questions = questions
                    )
                }


            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                // Failure
                _uiState.value = QuestionUiState(
                    isFailure = true,
                    isLoading = false
                )
            }
        }
    }


    fun addQuestionTrueFalse(text: String, correctAnswer : Boolean) {
        _addQuestionUiState.value = _addQuestionUiState.value.copy(
            isLoading = true
        )

        val question = Question.TrueFalse(
            id = UUID.randomUUID().toString(),
            taskId = "0173d456-2289-4a54-9226-719990e914b3",
            text = text,
            correctAnswer = correctAnswer
        )
        viewModelScope.launch {
            try {
                questionRepository.addQuestion(question)
                _addQuestionUiState.value = AddQuestionUiState(
                    isSuccess = true
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _addQuestionUiState.value = AddQuestionUiState(
                    errorMessage = e.message
                )
            }

        }
    }


    fun addQuestionMultipleChoice(text: String, correctAnswer: Int, answers: List<String>) {
        _addQuestionUiState.value = _addQuestionUiState.value.copy(
            isLoading = true
        )
        val question = Question.MultipleChoice(
            id = UUID.randomUUID().toString(),
            taskId = "0173d456-2289-4a54-9226-719990e914b3",
            text = text,
            correctAnswer = correctAnswer,
            answers = answers
        )
        viewModelScope.launch {
            try {
                questionRepository.addQuestion(question)
                _addQuestionUiState.value = AddQuestionUiState(
                    isSuccess = true
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _addQuestionUiState.value = AddQuestionUiState(
                    errorMessage = e.message
                )
            }

        }
    }
}






