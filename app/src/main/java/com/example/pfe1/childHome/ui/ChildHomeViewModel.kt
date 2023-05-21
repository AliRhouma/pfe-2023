package com.example.pfe1.childHome.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pfe1.childHome.data.ChildHomeRepositoryImpl
import com.example.pfe1.childHome.domain.repository.ChildHomeRepository
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.example.pfe1.kidsView.ui.AddChildUiState
import com.example.pfe1.kidsView.ui.ChildUiState
import com.example.pfe1.kidsView.ui.DeleteChildUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception

class ChildHomeViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val childRepository : ChildRepository = ChildRepositoryImpl()

    val childId = savedStateHandle.get<String>("childId")?:""

        private val _uiState = MutableStateFlow(ChildHomeUiState())
        val uiState = _uiState.asStateFlow()

        init {
            getChildData(childId = childId)
        }

    fun onEvent(event: ChildHomeEvents) {
        when (event) {
            is ChildHomeEvents.GetChildData -> getChildData(childId)
        }
    }

    private fun getChildData(childId: String) {
        _uiState.value = ChildHomeUiState(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                val child = childRepository.getChild(childId).collect(){ child ->
                    _uiState.value = ChildHomeUiState(
                        isLoading = false,
                        child = child
                    )
                }


            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _uiState.value = ChildHomeUiState(
                    errorMessage = e.message,
                    isLoading = false
                )
            }
        }
    }
}