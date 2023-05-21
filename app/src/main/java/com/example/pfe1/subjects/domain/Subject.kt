package com.example.pfe1.subjects.domain

import android.content.IntentSender.OnFinished
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects

data class Subject(
    val id: String,
    val name: String,
    val type: Subjects,
    val schoolYear: SchoolYear
)
