package com.example.pfe1.idGenerator

import android.content.ContentValues.TAG
import android.util.Log
import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.kidsView.domain.model.Child
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class IdGeneratorRepositoryImpl : IdgeneretorRepository {
    private val idCollection = Firebase.firestore.collection("ids")



override suspend fun updateLastId(id: String) {
    val id = getLastId()
    val newId = (id.toLong()+1).toString()
    val idGeneratorRemote = IdGeneratorRemote(newId)
    idCollection
        .document("nwsSdCVClnW86dozO6Zq")
        .set(idGeneratorRemote)
        .await()
}

    override fun getLastId(): String {
        val docRef = idCollection.document("nwsSdCVClnW86dozO6Zq")

        val scope = CoroutineScope(Dispatchers.IO)

        val deferredId = scope.async {
            try {
                val document = docRef.get().await()  // Wait for the query to complete
                if (document.exists()) {
                    val idGeneratorRemote = document.toObject(IdGeneratorRemote::class.java)
                    idGeneratorRemote?.numberOfUsers ?: "defaultId"
                } else {
                    "defaultId"
                }
            } catch (exception: Exception) {
                Log.d(TAG, "get failed with ", exception)
                "defaultId"
            }
        }

        return runBlocking { deferredId.await() }
    }
}





