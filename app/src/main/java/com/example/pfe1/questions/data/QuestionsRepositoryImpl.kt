package com.example.pfe1.questions.data

import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.questions.domain.repository.QuestionsRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

private val questionsCollection = Firebase.firestore.collection("questions")


class QuestionsRepositoryImpl: QuestionsRepository {

    override fun getQuestions(taskId: String): Flow<List<Question>> = callbackFlow {
        questionsCollection.whereEqualTo("taskId", taskId).addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener


            val response = value.toObjects(QuestionRemote::class.java).mapNotNull{ it?.toQuestion()}
            trySend(response)

                

            }


        awaitClose { cancel() }
    }

    override suspend fun addQuestion(question: Question) {
        val questionRemote = QuestionRemote.fromQuestion(question)

        questionsCollection
            .document(questionRemote.id)
            .set(questionRemote)
            .await()
    }

    override suspend fun updateQuestion(question: Question) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteQuestion(id: String) {
        TODO("Not yet implemented")
    }
}