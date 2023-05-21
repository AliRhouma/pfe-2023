package com.example.pfe1.subjects.domain

import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
     fun getSubjects(childId : String) : Flow<List<Subject>>
    fun getSubject(subjectId : String): Flow<Subject>
    suspend fun addSubject(subject: Subject)
}