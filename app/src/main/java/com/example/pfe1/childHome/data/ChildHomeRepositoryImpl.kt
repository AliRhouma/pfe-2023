package com.example.pfe1.childHome.data

import com.example.pfe1.childHome.domain.repository.ChildHomeRepository
import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.kidsView.domain.repository.ChildRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ChildHomeRepositoryImpl: ChildHomeRepository {


}

