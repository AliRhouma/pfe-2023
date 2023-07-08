package com.example.pfe1.register.data

import com.example.pfe1.enumClass.UserType
import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.register.domain.RegisterRepository
import com.example.pfe1.register.domain.User
import com.example.pfe1.subjects.data.remote.SubjectRemote
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class RegisterRepositoryImpl : RegisterRepository {
    private val userCollection = Firebase.firestore.collection("users")

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        userType: UserType,
    ) {
        val result = Firebase.auth
            .createUserWithEmailAndPassword(email, password)
            .await()

        var userRemote = UserRemote(
            id = result.user?.uid ?: "",
            name = name,
            email = email,
            userType = userType,
            schoolId = null,
            classId = null,
            classIdList = emptyList(),
        )

        userCollection.document(result.user?.uid ?: "",)
            .set(userRemote)
            .await()

    }

    override fun getUser(id: String): Flow<UserRemote> = callbackFlow {

        userCollection.document(id).addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObject(UserRemote::class.java) ?: return@addSnapshotListener
            trySend(response)
        }

        awaitClose { cancel() }
    }



    override fun getTeachersBySchoolId(schoolId: String): Flow<List<UserRemote>> = callbackFlow{
        userCollection.whereArrayContains("schoolIdList", schoolId)
            .addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObjects(UserRemote::class.java).mapNotNull { it}
            trySend(response)
        }

        awaitClose { cancel() }

    }

    override fun getTeachersByClassId(classId: String): Flow<List<UserRemote>> = callbackFlow{
        println("ooooooooooooooooooooooooo ${classId}")
        userCollection.whereArrayContains("classIdList", classId)
            .addSnapshotListener { value, error ->
                if (error != null) throw error
                if (value == null) return@addSnapshotListener

                val response = value.toObjects(UserRemote::class.java).mapNotNull { it}
                trySend(response)
            }

        awaitClose { cancel() }

    }

    override suspend fun updateUser(user: UserRemote) {
            userCollection.document(user.id).set(user).await()
        }
        }





    /*override fun getUser(parentId: String): Flow<List<User>> = callbackFlow {
        userCollection.whereEqualTo("parentId", parentId).addSnapshotListener { value, error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val response = value.toObjects(UserRemote::class.java).mapNotNull { it?.toUser() }

            trySend(response)
        }

        awaitClose { cancel() }
    }*/

