package com.example.pfe1.kidsView.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pfe1.kidsView.ui.BottomNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()



    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color(0xFFBED9E4),
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color(0xFF289ACF),
                unselectedContentColor = Color(0xFF1984A7),
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if(item.badgeCount > 0) {
                            BadgedBox( badge = {
                                Badge {
                                    val badgeNumber = item.badgeCount.toString()
                                    Text(
                                        badgeNumber,
                                        modifier = Modifier.semantics {
                                            contentDescription = "$badgeNumber new notifications"
                                        }
                                    )
                                }
                            })
                             {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}