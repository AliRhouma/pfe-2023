package com.example.pfe1.subjects.data

import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.example.pfe1.subjects.data.remote.SubjectRemote
import com.example.pfe1.subjects.domain.Subject
import com.example.pfe1.subjects.domain.SubjectRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class SubjectRepositoryImpl: SubjectRepository {

    private val childRepository: ChildRepository = ChildRepositoryImpl()
    private val subjectsCollection = Firebase.firestore.collection("subjects")

    override fun getSubjects(childId : String): Flow<List<Subject>> = callbackFlow {
        val childSchoolYear = childRepository.getChild(childId).map { child -> child.schoolYear }.first()
        val query = subjectsCollection.whereEqualTo("schoolYear", childSchoolYear)

        val listenerRegistration = query.addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val subjects = value.toObjects(SubjectRemote::class.java).mapNotNull { it.toSubject() }
            trySend(subjects)
        }
        awaitClose { cancel() }
    }

    override fun getSubject(subjectId : String): Flow<Subject> = callbackFlow {
        subjectsCollection.document(subjectId).addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObject(SubjectRemote::class.java) ?: return@addSnapshotListener
            trySend(response.toSubject())
        }

        awaitClose { cancel() }
    }




        override suspend fun addSubject(subject: Subject) {
        val subjectRemote = SubjectRemote.fromSubject(subject)
        subjectsCollection
            .document(subject.id)
            .set(subjectRemote)
            .await()
    }
}