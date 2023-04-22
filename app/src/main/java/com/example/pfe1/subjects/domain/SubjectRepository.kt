package com.example.pfe1.subjects.domain

interface SubjectRepository {
    suspend fun getAllSubject() : List<Subject>
    suspend fun addSubject(subject: Subject)
}