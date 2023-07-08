package com.example.pfe1.questions.domain.repository

import com.example.pfe1.questions.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    fun getAllQuestions(taskId: String): Flow<List<Question>>
    //fun getAllQuestions(): Flow<List<Question>>

    suspend fun addQuestion(question: Question)
    suspend fun updateQuestion(question: Question)
    suspend fun deleteQuestion(id: String)
}