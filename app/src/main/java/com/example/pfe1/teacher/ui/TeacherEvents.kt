package com.example.pfe1.teacher.ui

sealed class TeacherEvents {
    data class JoinScchool(
        val schoolId : String,
    ) : TeacherEvents()
    data class getClasses(
        val teacherid: String
    ) : TeacherEvents()
    data class GetTeacherData(
        val teacherid: String
    ) : TeacherEvents()

}
