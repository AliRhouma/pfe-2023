package com.example.pfe1.register.domain

import com.example.pfe1.enumClass.UserType
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.register.data.UserRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface RegisterRepository {
    suspend fun register(name : String,email : String, password : String, userType: UserType)
    fun getUser(id: String): Flow<UserRemote>

    fun getTeachersBySchoolId(schoolId: String): Flow<List<UserRemote>>
    fun getTeachersByClassId(schoolId: String): Flow<List<UserRemote>>

     suspend fun updateUser(user: UserRemote)




}