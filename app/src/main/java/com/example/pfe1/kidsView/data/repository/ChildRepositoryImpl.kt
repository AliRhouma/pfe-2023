package com.example.pfe1.kidsView.data.repository

import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

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