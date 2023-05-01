package com.example.pfe1.kidsView.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.UUID

class ChildViewModel : ViewModel() {
    private val childRepository: ChildRepository = ChildRepositoryImpl()

    private val _uiState = MutableStateFlow(ChildUiState())
    val uiState = _uiState.asStateFlow()

    private val _addChildUiState = MutableStateFlow(AddChildUiState())
    val addChildUiState = _addChildUiState.asStateFlow()

    private val _deleteChildUiState = MutableStateFlow(DeleteChildUiState())
    val deleteChildUiState = _deleteChildUiState.asStateFlow()

    init {
        getChilds()
    }

    fun onEvent(event: ChildEvents) {
        when (event) {
            is ChildEvents.AddChild -> addChild(
                name = event.name,
                schoolYear = event.schoolYear,
                imageUrl = event.imageUrl
            )

            is ChildEvents.ClearAddChild -> clearAddChild()
            is ChildEvents.Delete -> deleteChild(id = event.id)
            else -> {}
        }
    }


    private fun getChilds() {
        _uiState.value = ChildUiState(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                childRepository.getAllChilds().collect { childs ->
                    _uiState.value = ChildUiState(
                        childList = childs
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _uiState.value = ChildUiState(
                    errorMessage = e.message
                )
            }

        }

    }

    private fun addChild(name: String, schoolYear: String, imageUrl: String) {
        _addChildUiState.value = AddChildUiState(
            isLoading = true
        )

        val child = Child(
            id = UUID.randomUUID().toString(),
            name = name,
            schoolYear = schoolYear,
            imageUrl = imageUrl
        )

        viewModelScope.launch {
            try {
                childRepository.addChild(child)
                _addChildUiState.value = AddChildUiState(
                    isSuccess = true
                )
            } catch (e: Exception) {
                e.printStackTrace()

                // Failure
                _addChildUiState.value = AddChildUiState(
                    errorMessage = e.message
                )
            }

        }


    }

    private fun clearAddChild() {
        _addChildUiState.value = AddChildUiState()
    }

    private fun deleteChild(id: String) {
        _deleteChildUiState.value = DeleteChildUiState(isLoading = true)

        viewModelScope.launch {
            try {
                childRepository.deleteChild(id)
                _deleteChildUiState.value = DeleteChildUiState(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                // Failure
                _deleteChildUiState.value = DeleteChildUiState(
                    errorMessage = e.message ?: ""
                )
            }
        }
    }
}