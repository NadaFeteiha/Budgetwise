package com.nadafeteiha.budgetwise.ui.screen.home

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
import androidx.compose.runtime.getValue
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
import com.nadafeteiha.budgetwise.ui.screen.userInfo.UserInfoDialog
import com.nadafeteiha.budgetwise.ui.theme.Purple80
import com.nadafeteiha.budgetwise.util.toDoubleOrZero

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(state = state, listener = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
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
            ModalBottomSheet(
                onDismissRequest = { listener.onUpdateBottomSheetVisibility(false) },
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    if (state.selectedCategoryId == null) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            text = stringResource(id = R.string.select_save),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        LazyColumn {
                            items(state.categories) { category ->
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                listener.onCategorySelected(
                                                    category.id
                                                )
                                            }
                                            .padding(16.dp),
                                        text = category.title
                                    )
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 16.dp, end = 16.dp)
                                    )
                                }
                            }
                        }
                    } else {
                        SpendAmount(
                            spendValue = state.selectedSpendValue,
                            onValueChange = listener::onSpentValueChange,
                            onDoneClicked = listener::onDoneClicked,
                            onCancelClicked = listener::onUpdateBottomSheetVisibility,
                            title = state.categories.find { it.id == state.selectedCategoryId }?.title
                                ?: ""
                        )
                    }
                }
            }
        }
    }

    if (state.showDialog) {
        UserInfoDialog(onDismiss = listener::onDialogDismiss)
    }
}

@Composable
private fun SpendAmount(
    modifier: Modifier = Modifier,
    title: String,
    spendValue: Double,
    onValueChange: (Double) -> Unit,
    onDoneClicked: () -> Unit,
    onCancelClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1
        )

        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = "$spendValue",
            onValueChange = { onValueChange(it.toDoubleOrZero()) },
            label = { Text(stringResource(id = R.string.spend_amount)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
            singleLine = true,
            placeholder = { Text(stringResource(id = R.string.spend_amount)) })

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = onCancelClicked,
                shape = RoundedCornerShape(4.dp),
            ) {
                Text(
                    stringResource(id = R.string.cancel), modifier = Modifier.padding(start = 8.dp)
                )
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = onDoneClicked,
                enabled = spendValue != 0.0,
                shape = RoundedCornerShape(4.dp),
            ) {
                Text(
                    stringResource(id = R.string.save), modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

