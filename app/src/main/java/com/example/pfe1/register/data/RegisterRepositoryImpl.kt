package com.example.pfe1.register.data

import com.example.pfe1.register.domain.RegisterRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RegisterRepositoryImpl : RegisterRepository {
    override suspend fun register(name: String, email: String, password: String) {
        val result = Firebase.auth
            .createUserWithEmailAndPassword(email,password)
            .await()

        val userCollection = Firebase.firestore.collection("users")
        userCollection.add(UserRemote(
            id = result.user?.uid ?: "",
            name = name,
            email = email
        )).await()
    }
}
