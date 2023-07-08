package com.example.pfe1.classes.model

import kotlinx.coroutines.flow.Flow

interface ClassesRepository {

    fun getClassesBySchoolId(id: String): Flow<List<Classes>>
    fun getClassesByTeacherId(id: String): Flow<List<Classes>>
    suspend fun addClass(classes: Classes)
    suspend fun updateClass(classes: Classes)
    suspend fun deleteClass(classesId: String)

}