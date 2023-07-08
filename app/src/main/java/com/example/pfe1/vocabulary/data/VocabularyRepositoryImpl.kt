package com.example.pfe1.vocabulary.data

import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.vocabulary.domain.Vocabulary
import com.example.pfe1.vocabulary.domain.VocabularyRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class VocabularyRepositoryImpl: VocabularyRepository {
    private val vocabularyCollection = Firebase.firestore.collection("vocabulary")


    override fun getVocabularyBySubjectId(subjectId: String): Flow<List<Vocabulary>> = callbackFlow {
        vocabularyCollection.whereEqualTo("subjectId", subjectId).addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObjects(VocabularyRemote::class.java).mapNotNull { it?.toVocabulary() }
            trySend(response)
        }

        awaitClose { cancel() }
    }

    override suspend fun addVocabulary(vocabulary: Vocabulary) {
        val vocabularyRemote = VocabularyRemote.fromVocabulary(vocabulary)

        vocabularyCollection
            .document(vocabulary.id)
            .set(vocabularyRemote)
            .await()
    }

    override suspend fun updateVocabulary(vocabulary: Vocabulary) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteVocabulary(VocabularyId: String) {
        TODO("Not yet implemented")
    }

}


