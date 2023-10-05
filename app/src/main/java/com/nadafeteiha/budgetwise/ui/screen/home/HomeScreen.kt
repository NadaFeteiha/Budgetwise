package com.nadafeteiha.budgetwise.ui.screen.home

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadafeteiha.budgetwise.ui.screen.home.composable.BudgetCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    BudgetCard(
        modifier = Modifier.statusBarsPadding(),
        state = state
    )
}
