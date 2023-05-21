package com.example.pfe1.subjects.data.local

import android.content.IntentSender.OnFinished
import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.subjects.domain.Subject
import java.time.Year

data class SubjectLocal(
    val id : String,
    val name : String,
    val type : Subjects,
    val schoolYear: SchoolYear,
) {
    fun toSubject() = Subject(
        id = id,
        name = name,
        type = type,
        schoolYear = schoolYear,
    )
}
