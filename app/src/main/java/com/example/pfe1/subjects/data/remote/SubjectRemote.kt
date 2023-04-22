package com.example.pfe1.subjects.data.remote

import com.example.pfe1.subjects.domain.Subject

data class SubjectRemote(
    val id : String = "",
    val name : String = ""
) {
    fun toSubject() = Subject(
        id = id,
        name = name
    )

    companion object {
        fun fromSubject(subject: Subject): SubjectRemote {
            return SubjectRemote(
                id = subject.id,
                name = subject.name
            )
        }
    }
}
