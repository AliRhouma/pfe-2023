package com.example.pfe1.vocabulary.data

import com.example.pfe1.vocabulary.domain.Vocabulary

data class VocabularyRemote(
    val id: String = "",
    val chapterId: String = "",
    val iconUrl: String = "",
    val name: String = "",
) {
    fun toVocabulary(): Vocabulary {
        return Vocabulary(
            id = id,
            chapterId = chapterId,
            name = name,
            iconUrl = iconUrl,
        )
    }

    companion object {
        fun fromVocabulary(vocabulary: Vocabulary): VocabularyRemote {
            return VocabularyRemote(
                id = vocabulary.id,
                name = vocabulary.name,
                chapterId = vocabulary.chapterId,
                iconUrl = vocabulary.iconUrl
            )
        }
    }
}
