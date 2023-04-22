package com.example.pfe1.login.data

import com.example.pfe1.login.domain.LoginRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(email: String, password: String) {
        Firebase.auth
            .signInWithEmailAndPassword(email,password)
            .await()
    }
}