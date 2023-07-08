package com.example.pfe1.kidsView.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.example.pfe1.register.data.RegisterRepositoryImpl
import com.example.pfe1.register.domain.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.UUID

class ChildViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val childRepository: ChildRepository = ChildRepositoryImpl()
    private val registerRepository: RegisterRepository = RegisterRepositoryImpl()

    private val parentId = savedStateHandle.get<String>("parentId")?:""

    private val _uiState = MutableStateFlow(ChildUiState())
    val uiState = _uiState.asStateFlow()

    private val _parentNameUiState = MutableStateFlow(ParentNameUiState())
    val parentNameUiState = _parentNameUiState.asStateFlow()

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
            is ChildEvents.GetParentName -> getParentName(parentId = parentId)
            else -> {}
        }
    }


    private fun getChilds() {
        _uiState.value = ChildUiState(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                childRepository.getAllChilds(parentId).collect { childs ->
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

    private fun addChild(name: String, schoolYear: SchoolYear, imageUrl: String) {
        _addChildUiState.value = AddChildUiState(
            isLoading = true
        )

        val child = Child(
            id = UUID.randomUUID().toString(),
            parentId = parentId,
            name = name,
            schoolYear = schoolYear,
            imageUrl = imageUrl,
            schoolId = "4pNf4BT913VDJkOlbQ4bFHgYgwI3",
            classId = "33d2b161-4d02-4762-8fad-143773287c26"
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

    private fun getParentName(parentId: String) {
        _parentNameUiState.value = ParentNameUiState(
            isLoading = true
        )

        viewModelScope.launch {
            try {
                registerRepository.getUser(parentId).collect{
                    _parentNameUiState.value = ParentNameUiState(
                        isLoading = false,
                        parentName = it.name
                    )
                }
            } catch (e: Exception) {
                _parentNameUiState.value = ParentNameUiState(
                    isFailure = true
                )

            }
        }

    }
}