package com.nadafeteiha.budgetwise.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadafeteiha.budgetwise.ui.composable.FloatingButton
import com.nadafeteiha.budgetwise.ui.composable.ROUTE_CATEGORY
import com.nadafeteiha.budgetwise.ui.theme.LocalNavController

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = "Welcome ${state.name}!",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Budget $${state.budget}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        FloatingButton(modifier = Modifier
            .align(Alignment.BottomEnd)
            .background(Color.Transparent),
            icon = Icons.Default.List,
            onClick = { navController.navigate(ROUTE_CATEGORY) })
    }

}
