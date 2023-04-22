package com.example.pfe1.subjects.data

import com.example.pfe1.subjects.data.remote.SubjectRemote
import com.example.pfe1.subjects.domain.Subject
import com.example.pfe1.subjects.domain.SubjectRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SubjectRepositoryImpl: SubjectRepository {
    private val subjectsCollection = Firebase.firestore.collection("subjects")

    override suspend fun getAllSubject(): List<Subject> {
        return subjectsCollection
            .get()
            .await()
            .documents
            .mapNotNull { it?.toObject<SubjectRemote>()?.toSubject() }
    }

    override suspend fun addSubject(subject: Subject) {
        TODO("Not yet implemented")
    }
}