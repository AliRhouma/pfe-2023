package com.example.pfe1.vocabulary.ui

sealed class VocabularyTasksEvents {
    data class AddVocabulary (
        val name : String,
        val iconUrl: String,
        val chapterId: String,
    ) : VocabularyTasksEvents()

    data class GetVocabulary (
            val subjectId : String,
    ) : VocabularyTasksEvents()


}
