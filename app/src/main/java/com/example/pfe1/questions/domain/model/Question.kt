package com.example.pfe1.questions.domain.model

sealed class Question {

    data class TrueFalse(
        val id: String,
        val taskId: String,
        val text: String,
        val correctAnswer: Boolean,
    ): Question()

    data class MultipleChoice(
        val id: String,
        val taskId: String,
        val text: String,
        val correctAnswer: Int,
        val answers: List<String>,
    ): Question()

    data class Unknown(
        val id: String,
        val taskId: String,
        val text: String,
    ): Question()
}


