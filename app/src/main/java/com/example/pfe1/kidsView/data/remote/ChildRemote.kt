package com.example.pfe1.kidsView.data.remote

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.domain.model.Child

data class ChildRemote(
    val id: String = "",
    val parentId: String = "",
    val name: String = "",
    val schoolYear: SchoolYear = SchoolYear.DEFAULT,
    val imageUrl : String = "",
    val schoolId: String = "",
    val classId: String = "",
    val studentId: String = ""

    ) {
    fun toChild(): Child {
        return Child(
            id = id,
            parentId = parentId,
            name = name,
            schoolYear = schoolYear,
            imageUrl = imageUrl,
            schoolId = schoolId,
            classId = classId,
            studentId= studentId
        )
    }

    companion object{
        fun fromChild(child: Child): ChildRemote {
           return ChildRemote(
               id = child.id,
               parentId = child.parentId,
               name = child.name,
               schoolYear = child.schoolYear,
               imageUrl = child.imageUrl,
               schoolId = child.schoolId,
               classId = child.classId,
               studentId= child.studentId
           )

        }
    }
}

