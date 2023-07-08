package com.example.pfe1.kidsView.ui.parentSettingsScreen

sealed class ParentSettingEvents{
        data class GetParentData(
            val parentId: String,
        ) : ParentSettingEvents()
        data class UpdateParentData (
            val parentId: String,
            val parentName: String
        ) : ParentSettingEvents()
        object Logout : ParentSettingEvents()
    }
