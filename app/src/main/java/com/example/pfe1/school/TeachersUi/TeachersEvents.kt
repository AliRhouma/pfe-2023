package com.example.pfe1.school.TeachersUi

import com.example.pfe1.teacher.ui.TeacherEvents

sealed class TeachersEvents {
    data class GetTeachers(
        val schoolId: String,
    ) : TeachersEvents()
    data class AddTeachers(
        val teacherId: String
    ) : TeachersEvents()
}
