package com.nadafeteiha.budgetwise

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nadafeteiha.budgetwise.ui.composable.BottomBar
import com.nadafeteiha.budgetwise.ui.composable.BottomNavigationScreens
import com.nadafeteiha.budgetwise.ui.composable.NavigationGraph
import com.nadafeteiha.budgetwise.ui.theme.BudgetwiseTheme
import com.nadafeteiha.budgetwise.ui.theme.LocalNavController
import com.nadafeteiha.budgetwise.ui.theme.Purple80
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            BudgetwiseTheme(
            ) {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        bottomBar = { BottomBar() },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {},
                                shape = CircleShape,
                                containerColor = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.background(Color.Transparent),
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = "Add",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        },
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
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
