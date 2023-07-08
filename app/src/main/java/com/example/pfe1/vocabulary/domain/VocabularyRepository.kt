package com.example.pfe1.vocabulary.domain

import kotlinx.coroutines.flow.Flow

interface VocabularyRepository {
    fun getVocabularyBySubjectId(subjectId: String): Flow<List<Vocabulary>>
    suspend fun addVocabulary(vocabulary: Vocabulary)
    suspend fun updateVocabulary(vocabulary: Vocabulary)
    suspend fun deleteVocabulary(VocabularyId: String)
}