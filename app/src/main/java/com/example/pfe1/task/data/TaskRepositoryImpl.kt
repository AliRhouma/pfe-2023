package com.example.pfe1.task.data

import com.example.pfe1.kidsView.data.repository.ChildRepositoryImpl
import com.example.pfe1.subjects.data.SubjectRepositoryImpl
import com.example.pfe1.task.domain.TaskRepository
import com.example.pfe1.task.domain.model.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class TaskRepositoryImpl: TaskRepository {

    private val tasksCollection = Firebase.firestore.collection("tasks")
    private val childRepository = ChildRepositoryImpl()
    private val subjectRepository = SubjectRepositoryImpl()

    override fun getTasks(childId: String, subjectId: String): Flow<List<Task>> = callbackFlow {
        val schoolYear = childRepository.getChild(childId).map {child ->  child.schoolYear}.first()
        val subject = subjectRepository.getSubject(subjectId).map {subject ->  subject.type}.first()

        val query = tasksCollection
            .whereEqualTo("schoolYear", schoolYear)
            .whereEqualTo("subjects", subject)


        query.addSnapshotListener{ value , error ->
            if (error != null) throw error
            if (value == null) return@addSnapshotListener

            val tasks = value.toObjects(TaskRemote::class.java).mapNotNull { it.toTask() }
            trySend(tasks)
        }
        awaitClose { cancel() }
    }

    override suspend fun addTask(task: Task) {
        val taskRemote = TaskRemote.fromTask(task)
        tasksCollection
            .document(task.id)
            .set(taskRemote)
            .await()
    }

}

