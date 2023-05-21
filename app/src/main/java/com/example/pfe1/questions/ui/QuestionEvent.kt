package com.example.pfe1.questions.ui

import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.subjects.ui.SubjectsEvent

sealed class QuestionEvent {
    sealed class AddQuestion : QuestionEvent(){
        data class TrueFalse(
            val text: String,
            val correctAnswer: Boolean,
        ): AddQuestion()

        data class MultipleChoice(
            val text: String,
            val correctAnswer: Int,
            val answers: List<String>,
            ): AddQuestion()
    }
    data class GetQuestions (
        val taskId: String
    ) : QuestionEvent()

}
