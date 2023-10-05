package com.nadafeteiha.budgetwise.ui.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.ui.screen.TrendsScreen
import com.nadafeteiha.budgetwise.ui.screen.home.HomeScreen


const val ROUTE_HOME = "home"
const val ROUTE_TRENDS = "trends"
const val ROUTE_TRANSACTIONS = "transactions"
const val ROUTE_COMMUNITY = "community"
const val ROUTE_PROFILE = "profile"


sealed class BottomNavigationScreens(var icon: Int, var route: String) {
    object Home : BottomNavigationScreens(R.drawable.home_icon, ROUTE_HOME)
    object Trends : BottomNavigationScreens(R.drawable.trends_icon, ROUTE_TRENDS)

    object Transactions : BottomNavigationScreens(R.drawable.transactions_icon, ROUTE_TRANSACTIONS)

    object Community : BottomNavigationScreens(R.drawable.community_icon, ROUTE_COMMUNITY)

    object Profile : BottomNavigationScreens(R.drawable.profile_icon, ROUTE_PROFILE)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Home.route
    ) {

        composable(ROUTE_HOME) { HomeScreen() }

        composable(route = ROUTE_TRENDS) { TrendsScreen() }

        composable(route = ROUTE_TRANSACTIONS) { TrendsScreen() }

        composable(route = ROUTE_COMMUNITY) { TrendsScreen() }

        composable(route = ROUTE_PROFILE) { TrendsScreen() }

    }
}
