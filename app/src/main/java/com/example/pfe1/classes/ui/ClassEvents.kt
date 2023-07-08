package com.example.pfe1.classes.ui

sealed class ClassEvents {
    data class GetStudents (
        val classesId: String
        ) : ClassEvents()

    data class GetTeachers (
        val classesId: String
            ): ClassEvents()

    data class MoveStudent (
        val classesDestinationId: String,
        ) : ClassEvents()

    data class DeleteStudent (
        val studentId: String,
        ): ClassEvents()
    data class AddStudent (
        val studentId: String,
    ): ClassEvents()
    data class AddTeacher (
        val teacherId: String,
    ): ClassEvents()
}
