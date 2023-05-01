package com.example.pfe1.kidsView.data.remote

import com.example.pfe1.kidsView.domain.model.Child

data class ChildRemote(
    val id: String = "",
    val name: String = "",
    val schoolYear: String = "",
    val imageUrl : String = "",
) {
    fun toChild(): Child {
        return Child(
            id = id,
            name = name,
            schoolYear = schoolYear,
            imageUrl = imageUrl
        )
    }

    companion object{
        fun fromChild(child: Child): ChildRemote {
           return ChildRemote(
               id = child.id,
               name = child.name,
               schoolYear = child.schoolYear,
               imageUrl = child.imageUrl
           )

        }
    }
}

