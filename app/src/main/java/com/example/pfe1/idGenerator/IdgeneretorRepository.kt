package com.example.pfe1.idGenerator

import com.example.pfe1.kidsView.domain.model.Child
import kotlinx.coroutines.flow.Flow

interface IdgeneretorRepository {
    suspend fun updateLastId(id: String)
    fun getLastId(): String

}