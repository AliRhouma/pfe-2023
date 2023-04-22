package com.example.pfe1.login.domain

interface LoginRepository {
    suspend fun login(email : String, password : String)

}