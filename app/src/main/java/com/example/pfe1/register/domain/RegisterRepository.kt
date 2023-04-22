package com.example.pfe1.register.domain

interface RegisterRepository {
    suspend fun register(name : String,email : String, password : String)

}