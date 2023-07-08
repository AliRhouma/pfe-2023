package com.example.pfe1.kidsView.ui

import com.example.pfe1.enumClass.SchoolYear

sealed class ChildEvents{
    data class Delete(
        val id: String,
    ): ChildEvents()

    data class AddChild(
        val name: String,
        val schoolYear: SchoolYear,
        val imageUrl: String
    ): ChildEvents()

    object GetParentName : ChildEvents()

    object ClearAddChild : ChildEvents()
}


