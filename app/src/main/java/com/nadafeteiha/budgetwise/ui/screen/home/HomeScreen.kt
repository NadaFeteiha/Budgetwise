package com.nadafeteiha.budgetwise.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.ui.screen.home.composable.BudgetCard
import com.nadafeteiha.budgetwise.ui.composable.FloatingButton
import com.nadafeteiha.budgetwise.ui.screen.home.composable.AppBar
import com.nadafeteiha.budgetwise.ui.screen.home.composable.BottomSheet
import com.nadafeteiha.budgetwise.ui.screen.userInfo.UserInfoDialog
import com.nadafeteiha.budgetwise.ui.theme.Purple80
import com.nadafeteiha.budgetwise.util.toDoubleOrZero
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HomeScreenContent(state = state, listener = viewModel)

    val message = stringResource(id = R.string.exceed_valid_amount)

    LaunchedEffect(state.isValidAmountToSpend) {
        if (!state.isValidAmountToSpend) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
private fun HomeScreenContent(
    state: HomeUIState,
    listener: HomeInteractions
) {
    val screenHeight = LocalDensity.current.run {
        LocalContext.current.resources.displayMetrics.heightPixels.toDp()
    }
    val topRectangleHeight = screenHeight / 5

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .drawWithContent {
                drawRoundRect(
                    color = Purple80,
                    topLeft = Offset(0f, -100f),
                    size = Size(this.size.width, topRectangleHeight.toPx()),
                    cornerRadius = CornerRadius(50f),
                )
                drawContent()
            }
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AppBar()
            BudgetCard(state = state)
        }


        FloatingButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(Color.Transparent)
                .padding(bottom = 16.dp, end = 16.dp),
            icon = Icons.Default.Add,
            onClick = { listener.onUpdateBottomSheetVisibility(true) }
        )

        if (state.bottomSheetVisible) {
            BottomSheet(
                selectedCategory = state.selectedCategory,
                selectedSpendValue = state.selectedSpendValue,
                isValidAmountToSpend = state.isValidAmountToSpend,
                categories = state.categories,
                listener = listener
            )
        }
    }

    if (state.showDialog) {
        UserInfoDialog(onDismiss = listener::onDialogDismiss)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )
    }
}



