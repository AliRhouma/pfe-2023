package com.example.pfe1.school.ui

sealed class SchoolEvents {
    data class AddClasses (
        val name : String,
        val schoolId : String,
        val grade : String
    ) : SchoolEvents()

    data class GetClasses (
        val schoolId: String
    ) : SchoolEvents()

    data class GetSchoolName (
        val schoolId: String
    ) : SchoolEvents()


}
