package com.example.pfe1.kidsView.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.pfe1.classes.domain.ClassesRemote
import com.example.pfe1.idGenerator.IdGeneratorRemote
import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resumeWithException

class ChildRepositoryImpl : ChildRepository{
    private val childCollection = Firebase.firestore.collection("childs")

    override fun getAllChilds(parentId: String): Flow<List<Child>> = callbackFlow {
        childCollection.whereEqualTo("parentId",parentId).addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObjects(ChildRemote::class.java).mapNotNull { it?.toChild() }

            trySend(response)
        }

        awaitClose { cancel() }
    }

    override fun getChild(id: String): Flow<Child> = callbackFlow {
        childCollection.document(id).addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObject(ChildRemote::class.java) ?: return@addSnapshotListener
            trySend(response.toChild())
        }

        awaitClose { cancel() }
    }

    override suspend fun getChildNormal(id: String): Child = suspendCancellableCoroutine { continuation ->
        val listener = childCollection.document(id).addSnapshotListener { value, error ->
            if (error != null) {
                continuation.resumeWithException(error)
                return@addSnapshotListener
            }

            if (value == null) {
                continuation.resumeWithException(NullPointerException("Snapshot is null"))
                return@addSnapshotListener
            }

            val response = value.toObject(ChildRemote::class.java)
            if (response == null) {
                continuation.resumeWithException(NullPointerException("Response is null"))
                return@addSnapshotListener
            }

            val child = response.toChild()

        }

        continuation.invokeOnCancellation {
            listener.remove() // Remove the snapshot listener if the coroutine is cancelled
        }
    }


    override fun getChildsByClassId(classId: String): Flow<List<Child>> = callbackFlow{
        childCollection.whereEqualTo("classId", classId).addSnapshotListener{value, error->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObjects(ChildRemote::class.java).mapNotNull{ it?.toChild()}
            trySend(response)
        }

        awaitClose{cancel()}
    }

    override fun getChildByStudentId(studentId: String): Child {
        var child: Child

        val scope = CoroutineScope(Dispatchers.IO)

        val deferredId = scope.async {
            try {
                val document = childCollection.whereEqualTo("classId", studentId).get()
                    .addOnSuccessListener {
                        child = it.toObjects(ChildRemote::class.java).mapNotNull { it?.toChild() }.first()
                        child
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Error getting documents: ", it)
                    }
            } catch (exception: Exception) {
                Log.d(TAG, "get failed with ", exception)
                "defaultId"
            }
        }
            //return runBlocking { deferredId.await() }
    }

    override fun getChildsBySchoolId(schoolId: String): Flow<List<Child>> = callbackFlow{
        childCollection.whereEqualTo("schoolId", schoolId).addSnapshotListener{value, error->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObjects(ChildRemote::class.java).mapNotNull{ it?.toChild()}
            trySend(response)
        }

        awaitClose{cancel()}
    }

    override suspend fun addChild(child: Child) {
        val childRemote = ChildRemote.fromChild(child)

        childCollection
            .document(child.id)
            .set(childRemote)
            .await()
    }

    override suspend fun updateChild(child: Child) {
        val childRemote = ChildRemote.fromChild(child)

        childCollection
            .document(child.id)
            .set(childRemote)
            .await()
    }

    override suspend fun deleteChild(id: String) {
        childCollection
            .document(id)
            .delete()
            .await()
    }


}