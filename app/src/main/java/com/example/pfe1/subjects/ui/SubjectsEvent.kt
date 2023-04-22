package com.example.pfe1.subjects.ui

import javax.security.auth.Subject

sealed class SubjectsEvent {

    data class Event1 (
        val id : String,
    ) : SubjectsEvent()

    object GetSubjects : SubjectsEvent()

}

object Ghhhh {
    var x : String = "alia"
}