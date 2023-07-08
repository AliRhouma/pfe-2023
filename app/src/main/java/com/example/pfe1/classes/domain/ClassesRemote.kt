package com.example.pfe1.classes.domain

import com.example.pfe1.classes.model.Classes
import com.example.pfe1.enumClass.SchoolYear

    data class ClassesRemote(
        val id: String = "",
        val name : String = "",
        val schoolId : String? = null,
        val grade : String = "",
    ) {
        fun toClasses(): Classes {
            return Classes(
                id = id,
                name = name,
                schoolId = schoolId ?: "",
                grade = grade
            )
        }

        companion object {
            fun fromClasses(classes: Classes): ClassesRemote {
                return ClassesRemote(
                    id = classes.id,
                    name = classes.name,
                    schoolId = classes.schoolId,
                    grade = classes.grade
                )
            }
        }
    }
