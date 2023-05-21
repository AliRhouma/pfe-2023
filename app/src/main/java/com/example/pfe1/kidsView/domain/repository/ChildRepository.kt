package com.example.pfe1.kidsView.domain.repository

import com.example.pfe1.kidsView.domain.model.Child
import kotlinx.coroutines.flow.Flow

interface ChildRepository {
    fun getAllChilds(parentId: String): Flow<List<Child>>
    suspend fun addChild(child: Child)
    suspend fun updateChild(child: Child)
    suspend fun deleteChild(id: String)
    fun getChild(id: String): Flow<Child>
}

