package com.example.pfe1.subjects.data.remote

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.subjects.domain.Subject

data class SubjectRemote(
    val id : String = "",
    val name : String = "",
    val type: Subjects = Subjects.DEFAULT,
    val numberOfTasks: Int = 0,
    val schoolYear: SchoolYear = SchoolYear.DEFAULT,
    val finishedTasks: Int = 0
) {
    fun toSubject() = Subject(
        id = id,
        name = name,
        type = type,
        schoolYear = schoolYear,
    )

    companion object {
        fun fromSubject(subject: Subject): SubjectRemote {
            return SubjectRemote(
                id = subject.id,
                name = subject.name,
                type = subject.type,
                schoolYear = subject.schoolYear,
            )
        }
    }
}
