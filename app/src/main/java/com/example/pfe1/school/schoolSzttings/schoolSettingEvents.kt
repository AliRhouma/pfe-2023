package com.example.pfe1.school.schoolSzttings

import com.example.pfe1.school.StudentsUi.StudentsEvents

sealed class SchoolSettingEvents {
    data class GetSchoolData(
        val schoolId: String,
    ) : SchoolSettingEvents()
    data class UpdateSchoolData (
        val schoolId: String,
        val schoolName: String
        ) : SchoolSettingEvents()
}
