package com.nadafeteiha.budgetwise.ui.screen.userInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.util.toDoubleOrZero
import com.nadafeteiha.budgetwise.util.toStringOrEmpty


@Composable
fun UserInfoDialog(
    viewModel: UserInfoViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    WelcomeDialogContent(
        name = state.name,
        budget = state.budget,
        onNameChange = viewModel::onNameValueChange,
        onBudgetChange = viewModel::onBudgetValueChange,
        onDismiss = {
            viewModel.onDoneClicked()
            onDismiss()
        }
    )
}

@Composable
private fun WelcomeDialogContent(
    name: String,
    onNameChange: (String) -> Unit,
    budget: Double,
    onBudgetChange: (Double) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 8.dp,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Enter your Info:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = name,
                    onValueChange = { onNameChange(it) },
                    label = { Text("Name") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    ),
                    placeholder = { Text("Name") },
                    singleLine = true
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = "$budget",
                    onValueChange = { onBudgetChange(it.toDoubleOrZero()) },
                    label = { Text(stringResource(id = R.string.budget)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal
                    ),
                    placeholder = { Text(stringResource(id = R.string.budget)) },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onDismiss() },
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(stringResource(id = R.string.save))
            }
        }
    )
}
