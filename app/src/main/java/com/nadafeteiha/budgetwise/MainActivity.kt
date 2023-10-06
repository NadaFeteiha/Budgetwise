package com.nadafeteiha.budgetwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nadafeteiha.budgetwise.ui.composable.BottomBar
import com.nadafeteiha.budgetwise.ui.composable.BottomNavigationScreens
import com.nadafeteiha.budgetwise.ui.composable.NavigationGraph
import com.nadafeteiha.budgetwise.ui.theme.BudgetwiseTheme
import com.nadafeteiha.budgetwise.ui.theme.LocalNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            BudgetwiseTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            val visibility =
                                currentRoute(navController) in listOf(
                                    BottomNavigationScreens.Home.route,
                                    BottomNavigationScreens.Profile.route,
                                )
                            BottomBar(visibility)
                        }) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(bottom = innerPadding.calculateBottomPadding())
                                .statusBarsPadding()
                                .fillMaxSize()
                        ) {
                            NavigationGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
