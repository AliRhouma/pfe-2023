package com.example.pfe1.questions.data

import com.example.pfe1.questions.domain.model.Question

data class QuestionRemote(
    // Common
    val id: String = "",
    // MultipleChoice
    val multipleChoice: MultipleChoiceRemote? = null,

    val taskId: String = "",
    val text: String = "",

    // TrueFalse
    val trueFalse: TrueFalseRemote? = null,

    val type: String = "",





) {
    fun toQuestion(): Question {
        return when (type) {
            "TrueFalse" -> Question.TrueFalse(
                id = id,
                taskId = taskId,
                text = text,
                correctAnswer = trueFalse?.correctAnswer ?: false,
            )
            "MultipleChoice" -> Question.MultipleChoice(
                id = id,
                taskId = taskId,
                text = text,
                correctAnswer = multipleChoice?.correctAnswer ?: -1,
                answers = multipleChoice?.answers ?: emptyList(),
            )
            else -> Question.Unknown(
                id = id,
                taskId = taskId,
                text = text,
            )
        }
    }

    companion object {
        fun fromQuestion(question: Question): QuestionRemote {
            return when (question) {
                is Question.TrueFalse -> QuestionRemote(
                    id = question.id,
                    taskId = question.taskId,
                    text = question.text,
                    type = "TrueFalse",
                    trueFalse = TrueFalseRemote(question.correctAnswer)
                )
                is Question.MultipleChoice -> QuestionRemote(
                    id = question.id,
                    taskId = question.taskId,
                    text = question.text,
                    type = "MultipleChoice",
                    multipleChoice = MultipleChoiceRemote(question.answers, question.correctAnswer)
                )
                is Question.Unknown -> QuestionRemote(
                    id = question.id,
                    taskId = question.taskId,
                    text = question.text,
                    type = "Unknown"
                )
            }
        }
    }
    data class TrueFalseRemote(
        val correctAnswer: Boolean = true,
    )

    data class MultipleChoiceRemote(
        val answers: List<String> = emptyList(),
        val correctAnswer: Int = 2,
    )
}






