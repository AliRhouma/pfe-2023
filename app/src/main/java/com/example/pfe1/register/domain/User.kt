package com.example.pfe1.register.domain

import com.example.pfe1.enumClass.Subjects
import com.example.pfe1.enumClass.UserType
import com.example.pfe1.questions.domain.model.Question

sealed class User {

    data class Parent(
        val id : String,
        val name : String,
        val email : String,
    ) : User()

    data class School(
        val id : String,
        val name : String,
        val email : String,
    ) : User()

    data class Teacher(
        val id : String,
        val name : String,
        val email : String,
        val subject : Subjects,
    ) : User()

    data class Unknown(
        val id : String,
        val name : String,
        val email : String,
    ) : User()
}

