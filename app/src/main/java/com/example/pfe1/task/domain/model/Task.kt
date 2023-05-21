package com.example.pfe1.task.domain.model

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects

data class Task(
    val id: String,
    val name: String,
    val schoolYear: SchoolYear,
    val subjects: Subjects,
    val taskDescription: String,
    val taskNum: Int,
)
