package com.example.pfe1.kidsView.ui

sealed class ChildEvents{
    data class Delete(
        val id: String
    ): ChildEvents()

    data class AddChild(
        val name: String,
        val schoolYear: String,
        val imageUrl: String
    ): ChildEvents()

    object ClearAddChild : ChildEvents()
}


