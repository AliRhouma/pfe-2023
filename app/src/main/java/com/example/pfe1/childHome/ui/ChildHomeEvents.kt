package com.example.pfe1.childHome.ui

import com.example.pfe1.childHome.domain.model.ChildHome

sealed class ChildHomeEvents {
    data class GetChildData(
        val childName: String,
    ) : ChildHomeEvents()

}

