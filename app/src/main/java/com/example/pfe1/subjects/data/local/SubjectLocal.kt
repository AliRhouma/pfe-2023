package com.example.pfe1.subjects.data.local

import com.example.pfe1.subjects.domain.Subject

data class SubjectLocal(
    val id : String,
    val name : String
) {
    fun toSubject() = Subject(
        id = id,
        name = name
    )
}
