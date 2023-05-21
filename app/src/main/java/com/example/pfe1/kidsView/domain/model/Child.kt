package com.example.pfe1.kidsView.domain.model

import com.example.pfe1.enumClass.SchoolYear

data class Child(
    val id: String,
    val parentId: String,
    val name: String,
    val schoolYear: SchoolYear,
    val imageUrl : String
)

