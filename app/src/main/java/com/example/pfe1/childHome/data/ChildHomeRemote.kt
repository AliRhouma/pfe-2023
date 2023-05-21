package com.example.pfe1.childHome.data

import com.example.pfe1.childHome.domain.model.ChildHome
import com.example.pfe1.kidsView.data.remote.ChildRemote
import com.example.pfe1.kidsView.domain.model.Child

data class ChildHomeRemote(
    var id: String,
    var childId: String,
    var schoolYear: String,
    var childName: String,
    var imageUrl: String
) {
    fun toChildHome(): ChildHome {
        return ChildHome(
            id = id,
            childId = childId,
            schoolYear = schoolYear,
            childName = childName,
            imageUrl = imageUrl
        )
    }

    companion object {
        fun fromChildHome(childHome: ChildHome): ChildHomeRemote {
            return ChildHomeRemote(
                id = childHome.id,
                childId = childHome.childId,
                schoolYear = childHome.schoolYear,
                childName = childHome.childName,
                imageUrl =  childHome.imageUrl
            )
        }
    }
}