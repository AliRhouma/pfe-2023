package com.example.pfe1.task.ui

import com.example.pfe1.subjects.ui.SubjectsEvent

sealed class TaskEvents {
    data class GetTasks(
        val schoolYear: String,
        val subjectId: String
    ): TaskEvents()
}
