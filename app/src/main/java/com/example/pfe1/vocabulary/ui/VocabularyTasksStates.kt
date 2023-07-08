package com.example.pfe1.vocabulary.ui

import com.example.pfe1.classes.model.Classes
import com.example.pfe1.questions.domain.model.Question
import com.example.pfe1.vocabulary.domain.Vocabulary

data class VocabularyTasksStates(
    val isLoading : Boolean = false,
    val isFailure : Boolean = false,
    val empty : Boolean = true,
    val vocabulary: List<Vocabulary> = emptyList(),
    val taskType : Question = Question.Unknown("","","")
)
