package com.example.pfe1.school.StudentsUi

sealed class StudentsEvents {
    data class GetStudents(
        val schoolId: String,
    ) : StudentsEvents()
    data class AddStudents(
        val StudentId: String
    ) : StudentsEvents()
}
