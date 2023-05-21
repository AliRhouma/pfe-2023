package com.example.pfe1.kidsView.data.remote

import com.example.pfe1.enumClass.SchoolYear
import com.example.pfe1.kidsView.domain.model.Child

data class ChildRemote(
    val id: String = "",
    val parentId: String = "",
    val name: String = "",
    val schoolYear: SchoolYear = SchoolYear.DEFAULT,
    val imageUrl : String = "",
) {
    fun toChild(): Child {
        return Child(
            id = id,
            parentId = parentId,
            name = name,
            schoolYear = schoolYear,
            imageUrl = imageUrl
        )
    }

    companion object{
        fun fromChild(child: Child): ChildRemote {
           return ChildRemote(
               id = child.id,
               parentId = child.parentId,
               name = child.name,
               schoolYear = child.schoolYear,
               imageUrl = child.imageUrl
           )

        }
    }
}

