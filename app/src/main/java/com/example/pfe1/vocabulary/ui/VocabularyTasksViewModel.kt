package com.example.pfe1.vocabulary.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.kidsView.ui.AddChildUiState
import com.example.pfe1.kidsView.ui.ChildUiState
import com.example.pfe1.vocabulary.data.VocabularyRepositoryImpl
import com.example.pfe1.vocabulary.domain.Vocabulary
import com.example.pfe1.vocabulary.domain.VocabularyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class VocabularyTasksViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val vocabularyRepository: VocabularyRepository = VocabularyRepositoryImpl()

    private val _VocabularyUiState = MutableStateFlow(VocabularyTasksStates())
    val vocabularyUiState = _VocabularyUiState.asStateFlow()

    val chapterId = savedStateHandle.get<String>("chapterId")?:""

    var vocabularyList : List<Vocabulary> = emptyList()


    private fun gelVocabularyByChapterId(chapterId: String) {
        _VocabularyUiState.value = _VocabularyUiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
                try {
                    vocabularyRepository.getVocabularyBySubjectId(chapterId).collect { vocabulary ->
                        vocabularyList = vocabulary
                    }
                } catch (e: Exception) {
                    e.printStackTrace()

                    // Failure

                }

            }
    }
    private fun addVocabulary(vocabulary: Vocabulary) {

    }







}