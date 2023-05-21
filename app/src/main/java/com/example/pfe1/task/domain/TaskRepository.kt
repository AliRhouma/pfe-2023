package com.example.pfe1.task.domain

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.domain.model.Child
import com.example.pfe1.task.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(childId: String, subjectId: String): Flow<List<Task>>
    suspend fun addTask(task: Task)
}