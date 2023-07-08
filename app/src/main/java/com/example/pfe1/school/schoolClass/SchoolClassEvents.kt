package com.example.pfe1.school.schoolClass

sealed class SchoolClassEvents {
    data class GetTeachersByClassId(
        val classId: String,
    ) : SchoolClassEvents()

    data class GetChildsByClassId(
        val classId: String,
    ) : SchoolClassEvents()

    data class GetChildsBySchoolId(
        val schoolId: String,
    ) : SchoolClassEvents()

    data class GetTeachersBySchoolId(
        val schoolId: String,
    ) : SchoolClassEvents()

    data class AddChild(
        val childId: String,
    ) : SchoolClassEvents()

    data class AddTeacher(
        val TeacherId: String,
    ) : SchoolClassEvents()

    data class DeleteChild(
        val childId: String,
    ) : SchoolClassEvents()

    data class DeleteTeacher(
        val teacherId: String,
    ) : SchoolClassEvents()

    data class MoveChild(
        val childId: String,
        val classIdDestination: String
    ) : SchoolClassEvents()
    data class GetAllStudents(
        val schoolId: String,
        val grade: String
    ) : SchoolClassEvents()
    data class GetAllTeachers(
        val schoolId: String,
    ) : SchoolClassEvents()



}