package com.example.pfe1.navigation

sealed class Screen(
    val route : String,
){
    object Login : Screen(route = "login")
    object Subjects : Screen(route  =  "subjects")
    object Register : Screen(route = "register")
    object Childs : Screen(route = "childs")
    object ChildHome : Screen(route = "childHome")
    object Tasks : Screen(route = "tasks")
    object Question : Screen(route = "question")
    object Settings : Screen(route = "settings")
    object Teacher : Screen(route = "teacher")
    object School : Screen(route = "school")
    object AdministrationClass : Screen(route = "administrationClass")
    object StudentsOfSchool : Screen(route = "studentsOfSchool")
    object TeachersScreen : Screen(route = "teacherScreen")
    object VocabularyTasksScreen : Screen(route = "vocabularyTasksScreen")
    object StudentsScreen : Screen(route = "studentScreen")
    object SchoolSettingsScreen : Screen(route = "schoolSettingsScreen")
    object ParentSettingsScreen : Screen(route = "parentSettingsScreen")
    object SchoolClassScreen : Screen(route = "schoolClassScreen")
    object ClassScreen : Screen(route = "ClassScreen")




}
