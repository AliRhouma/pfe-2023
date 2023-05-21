package com.example.pfe1.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
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
import com.example.pfe1.kidsView.ui.BottomNavItem
import com.example.pfe1.kidsView.ui.ChildsScreen
import com.example.pfe1.kidsView.ui.components.BottomNavigationBar
import com.example.pfe1.login.ui.LoginScreen
import com.example.pfe1.questions.ui.QuestionScreen
import com.example.pfe1.register.ui.RegisterScreen
import com.example.pfe1.settings.SettingsScreen
import com.example.pfe1.subjects.ui.SubjectsScreen
import com.example.pfe1.task.ui.TasksScreen

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
    var currentRoute by remember { mutableStateOf(Screen.Login.route) }


    val screensWithBottomBar = listOf<String>(Screen.Settings.route, Screen.Childs.route)
    Scaffold(
        bottomBar = {
            if (currentRoute in screensWithBottomBar) {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            name = "Home",
                            route = Screen.Childs.route,
                            icon = Icons.Default.Home
                        ),
                        BottomNavItem(
                            name = "Chat",
                            route = "chat",
                            icon = Icons.Default.Notifications,
                            badgeCount = 23
                        ),
                        BottomNavItem(
                            name = "Settings",
                            route = Screen.Settings.route,
                            icon = Icons.Default.Settings,
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
                currentRoute = Screen.Login.route
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
                currentRoute = Screen.Subjects.route
            }


            composable(
                route = Screen.Register.route
            ) {
                RegisterScreen(navController)
                currentRoute = Screen.Register.route

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
                currentRoute = Screen.Childs.route

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
                currentRoute = Screen.ChildHome.route

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
                currentRoute = Screen.Tasks.route
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
                currentRoute = Screen.Question.route

            }

            composable(
                route = Screen.Settings.route
            ) {
                SettingsScreen(navController)
                currentRoute = Screen.Settings.route

            }


        }
    }
}

