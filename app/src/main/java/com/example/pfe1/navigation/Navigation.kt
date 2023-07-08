package com.example.pfe1.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pfe1.childHome.ui.ChildHomeScreen
import com.example.pfe1.classes.ui.AdministrationClassScreen
import com.example.pfe1.kidsView.ui.BottomNavItem
import com.example.pfe1.kidsView.ui.ChildsScreen
import com.example.pfe1.kidsView.ui.components.BottomNavigationBar
import com.example.pfe1.kidsView.ui.parentSettingsScreen.ParentSettingsScreen
import com.example.pfe1.login.ui.LoginScreen
import com.example.pfe1.questions.ui.QuestionScreen
import com.example.pfe1.register.ui.RegisterScreen
import com.example.pfe1.school.TeachersUi.TeachersScreen
import com.example.pfe1.school.schoolClass.SchoolClassScreen
import com.example.pfe1.school.schoolSzttings.SchoolSettingsScreen
import com.example.pfe1.school.studentsUi.StudentsScreen
import com.example.pfe1.school.ui.SchoolScreen
import com.example.pfe1.school.ui.StudentOfSchoolScreen
import com.example.pfe1.settings.SettingsScreen
import com.example.pfe1.subjects.ui.SubjectsScreen
import com.example.pfe1.task.ui.TasksScreen
import com.example.pfe1.teacher.ui.ClassScreen
import com.example.pfe1.teacher.ui.TeacherScreen
import com.example.pfe1.vocabulary.ui.VocabularyTasksScreen

/*
composable(
route = Screen.<ScreenObjectName>.route +
"?<nameOfVarToPass> ={<varToPass>}&<nameOfVarToPass2> ={varToPass}",
arguments = listOf(
navArgument(
name = "nameOfVarToPass"
) {
    type = NavType.<typeOfVar>Type
    defaultValue = <defaultValueOfVar>
},
navArgument(
name = "noteColor"
) {
    type = NavType.<typeOfVar>Type
    defaultValue = <defaultValueOfVar>
},
)
) {
    val <color> = it.arguments?.getInt("noteColor") ?: -1
    AddEditNoteScreen(
        navController = navController,
        noteColor = <color>
    )
} */


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val screensWithBottomBar = listOf<String>()
    Scaffold(
        bottomBar = {
            val route = navBackStackEntry?.destination?.route
            if (screensWithBottomBar.any { route?.startsWith(it) == true }) {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Home",
                            route = Screen.Childs.route,
                            icon = Icons.Outlined.Home
                        ),
                        BottomNavItem(
                            name = "Chat",
                            route = Screen.Childs.route,
                            icon = Icons.Outlined.Notifications,
                            badgeCount = 23
                        ),
                        BottomNavItem(
                            name = "Settings",
                            route = Screen.Settings.route,
                            icon = Icons.Outlined.Settings,
                            badgeCount = 214
                        ),
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
        ) {

            composable(
                route = Screen.Login.route
            ) {
                LoginScreen(navController)
            }

            composable(
                route = Screen.ClassScreen.route
            ) {
                ClassScreen(navController)
            }

            //composable(
               // route = Screen.Teacher.route
         //   ) {
               // TeacherScreen(navController)
           // }

            composable(
                route = Screen.Teacher.route + "?teacherId={teacherId}",
                arguments = listOf(
                    navArgument(
                        name = "teacherId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val teacherId = it.arguments?.getString("teacherId") ?: ""
                println("ti 7ata mena")
                TeacherScreen(navController, teacherId)
            }

            composable(
                route = Screen.School.route + "?schoolId={schoolId}",
                arguments = listOf(
                    navArgument(
                        name = "schoolId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val schoolId = it.arguments?.getString("schoolId") ?: ""
                SchoolScreen(navController, schoolId)
            }


            composable(
                route = Screen.Subjects.route +
                        "?childId={childId}",
                arguments = listOf(
                    navArgument(
                        name = "childId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val childId = it.arguments?.getString("childId") ?: ""
                SubjectsScreen(navController, childId)
            }

            composable(
                route = Screen.AdministrationClass.route +
                        "?classesId={classesId}",
                arguments = listOf(
                    navArgument(
                        name = "classesId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val classesId = it.arguments?.getString("classesId") ?: ""
                AdministrationClassScreen(navController, classesId)
            }

            composable(
                route = Screen.VocabularyTasksScreen.route
                ) {
                VocabularyTasksScreen(navController)
            }

            composable(
                route = Screen.StudentsOfSchool.route +
                        "?schoolId={schoolId}",
                arguments = listOf(
                    navArgument(
                        name = "schoolId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val schoolId = it.arguments?.getString("schoolId") ?: ""
                StudentOfSchoolScreen(navController, schoolId)
            }

            composable(
                route = Screen.StudentsScreen.route +
                        "?schoolId={schoolId}",
                arguments = listOf(
                    navArgument(
                        name = "schoolId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val schoolId = it.arguments?.getString("schoolId") ?: ""
                StudentsScreen(navController, schoolId)
            }

            composable(
                route = Screen.TeachersScreen.route +
                        "?schoolId={schoolId}",
                arguments = listOf(
                    navArgument(
                        name = "schoolId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val schoolId = it.arguments?.getString("schoolId") ?: ""
                TeachersScreen(navController, schoolId)
            }

            composable(
                route = Screen.SchoolSettingsScreen.route +
                        "?schoolId={schoolId}",
                arguments = listOf(
                    navArgument(
                        name = "schoolId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val schoolId = it.arguments?.getString("schoolId") ?: ""
                SchoolSettingsScreen(navController, schoolId)
            }

            composable(
                route = Screen.ParentSettingsScreen.route +
                        "?parentId={parentId}",
                arguments = listOf(
                    navArgument(
                        name = "parentId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val parentId = it.arguments?.getString("parentId") ?: ""
                ParentSettingsScreen(navController, parentId)
            }


            composable(
                route = Screen.Register.route
            ) {
                RegisterScreen(navController)

            }

            composable(
                route = Screen.Childs.route + "?parentId={parentId}",
                arguments =
                listOf(
                    navArgument(
                        name = "parentId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val parentId = it.arguments?.getString("parentId") ?: ""
                ChildsScreen(
                    navController = navController,
                    parentId = parentId,
                    paddingValues = paddingValues
                )

            }

            composable(
                route = Screen.ChildHome.route + "?childId={childId}",
                arguments =
                listOf(
                    navArgument(
                        name = "childId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) {
                val childId = it.arguments?.getString("childId") ?: ""
                ChildHomeScreen(
                    navController = navController,
                    childId = childId,
                )

            }

            composable(
                route = Screen.SchoolClassScreen.route + "?schoolId={schoolId}&&classId={classId}",
                arguments =
                listOf(
                    navArgument(
                        name = "schoolId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(
                        name = "classId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )

            ) {
                val schoolId = it.arguments?.getString("schoolId") ?: ""
                val classId = it.arguments?.getString("classId") ?: ""
                SchoolClassScreen(
                    navController = navController,
                    schoolId = schoolId,
                    classId = classId
                )
            }

            composable(
                route = Screen.Tasks.route + "?childId={childId}&&subjectId={subjectId}",
                arguments =
                listOf(
                    navArgument(
                        name = "childId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(
                        name = "subjectId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )

            ) {
                val childId = it.arguments?.getString("childId") ?: ""
                val subjectId = it.arguments?.getString("subjectId") ?: ""
                TasksScreen(
                    navController = navController,
                    childId = childId,
                    subjectId = subjectId
                )
            }

            composable(
                route = Screen.Question.route + "?childId={childId}&taskId={taskId}&subjectId={subjectId}",
                arguments =
                listOf(
                    navArgument(
                        name = "childId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(
                        name = "taskId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(
                        name = "subjectId"
                    ) {
                        type = NavType.StringType
                        defaultValue = ""
                    }


                )

            ) {
                val childId = it.arguments?.getString("childId") ?: ""
                val taskId = it.arguments?.getString("taskId") ?: ""
                val subjectId = it.arguments?.getString("subjectId") ?: ""

                QuestionScreen(
                    navController = navController,
                    childId = childId,
                    taskId = taskId,
                    subjectId = subjectId
                )

            }

            composable(
                route = Screen.Settings.route
            ) {
                SettingsScreen(navController)

            }


        }
    }
}

