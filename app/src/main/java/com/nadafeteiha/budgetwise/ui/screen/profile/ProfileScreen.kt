package com.nadafeteiha.budgetwise.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nadafeteiha.budgetwise.ui.composable.FloatingButton
import com.nadafeteiha.budgetwise.ui.composable.ROUTE_CATEGORY
import com.nadafeteiha.budgetwise.ui.theme.LocalNavController

@Composable
fun ProfileScreen() {
    val navController = LocalNavController.current

    Box(modifier = Modifier.fillMaxSize()) {
        FloatingButton(modifier = Modifier
            .align(Alignment.BottomEnd)
            .background(Color.Transparent)
            .padding(bottom = 16.dp, end = 16.dp),
            icon = Icons.Default.List,
            onClick = { navController.navigate(ROUTE_CATEGORY) })
    }

}
