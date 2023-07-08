package com.example.pfe1.classes.domain

import com.example.pfe1.classes.model.Classes
import com.example.pfe1.classes.model.ClassesRepository
import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ClassesReositoryImpl: ClassesRepository {
    private val classesCollection = Firebase.firestore.collection("classes")


    override fun getClassesBySchoolId(id: String): Flow<List<Classes>> = callbackFlow{
        classesCollection.whereEqualTo("schoolId", id).addSnapshotListener{value, error->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObjects(ClassesRemote::class.java).mapNotNull{ it?.toClasses()}
            trySend(response)
        }
        awaitClose{cancel()}
    }

    override fun getClassesByTeacherId(id: String): Flow<List<Classes>> {
        TODO("Not yet implemented")
    }

    override suspend fun addClass(classes: Classes) {
        val classesRemote = ClassesRemote.fromClasses(classes)

        classesCollection
            .document(classes.id)
            .set(classesRemote)
            .await()
    }

    override suspend fun updateClass(classes: Classes) {
        val classesRemote = ClassesRemote.fromClasses(classes)

        classesCollection
            .document(classes.id)
            .set(classesRemote)
            .await()
    }

    override suspend fun deleteClass(classesId: String) {
        classesCollection
            .document(classesId)
            .delete()
            .await()
    }
}