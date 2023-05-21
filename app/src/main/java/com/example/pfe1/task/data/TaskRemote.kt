package com.example.pfe1.task.data

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.enumClass.toSchoolYear
import com.example.pfe1.enumClass.toSubjects
import com.example.pfe1.task.domain.model.Task

data class TaskRemote(
    val id: String = "",
    val name: String = "",
    val schoolYear: String = "",
    val subjects: String = "",
    val taskDescription: String = "",
    val taskNum: Int = -1,
) {
    fun toTask(): Task {
        return Task(
            id = id,
            name = name,
            schoolYear = toSchoolYear(schoolYear),
            subjects = toSubjects(subjects),
            taskDescription = taskDescription,
            taskNum = taskNum
        )
    }

    companion object{
        fun fromTask(task: Task): TaskRemote {
            return TaskRemote(
                id = task.id,
                name = task.name,
                schoolYear = task.schoolYear.toString(),
                subjects = task.subjects.toString(),
                taskDescription = task.taskDescription,
                taskNum = task.taskNum
            )
        }
    }
}
