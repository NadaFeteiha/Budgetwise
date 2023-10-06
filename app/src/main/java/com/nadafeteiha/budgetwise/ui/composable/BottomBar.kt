package com.nadafeteiha.budgetwise.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nadafeteiha.budgetwise.ui.theme.LocalNavController
import com.nadafeteiha.budgetwise.ui.theme.Purple80

@Composable
fun BottomBar(visibility: Boolean) {
    val navController = LocalNavController.current
    if (visibility) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntry?.destination

            val bottomNavItems = listOf(
                BottomNavigationScreens.Home,
                BottomNavigationScreens.Trends,
                BottomNavigationScreens.Transactions,
                BottomNavigationScreens.Community,
                BottomNavigationScreens.Profile,
            )

            bottomNavItems.forEach { item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                NavigationBarItem(
                    selected = selected,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Box(
                            modifier = Modifier.drawBehind {
                                if (selected) {
                                    val strokeWidth = 4.dp.toPx()
                                    val iconSize = size.width + 10
                                    val lineY = (strokeWidth / 2) - 10
                                    drawLine(
                                        color = Purple80,
                                        start = Offset(0f, lineY),
                                        end = Offset(iconSize, lineY),
                                        strokeWidth = strokeWidth
                                    )
                                }
                            }
                        ) {
                            Icon(painterResource(id = item.icon), contentDescription = null)
                        }
                    },
                    label = {
                        Text(
                            text = item.route,
                            maxLines = 1
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                        indicatorColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    }
}
