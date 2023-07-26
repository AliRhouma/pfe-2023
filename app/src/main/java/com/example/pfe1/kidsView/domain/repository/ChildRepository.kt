package com.example.pfe1.kidsView.domain.repository

import com.example.pfe1.kidsView.domain.model.Child
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface ChildRepository {
    fun getAllChilds(parentId: String): Flow<List<Child>>
    suspend fun addChild(child: Child)
    suspend fun updateChild(child: Child)
    suspend fun deleteChild(id: String)
    fun getChild(id: String): Flow<Child>
    suspend fun getChildNormal(id: String): Child
    fun getChildsByClassId(classId: String): Flow<List<Child>>
    fun getChildByStudentId(studentId: String): Child

    fun getChildsBySchoolId(schoolId: String): Flow<List<Child>>


}

