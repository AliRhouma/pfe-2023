package com.example.pfe1.subjects.ui

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.enumClass.Subjects

sealed class SubjectsEvent {

    data class AddSubject (
        val name : String,
        val schoolYear : SchoolYear,
        val type: Subjects
        ) : SubjectsEvent()

    data class GetSubjects (
        val childId: String
        ) : SubjectsEvent()

}
