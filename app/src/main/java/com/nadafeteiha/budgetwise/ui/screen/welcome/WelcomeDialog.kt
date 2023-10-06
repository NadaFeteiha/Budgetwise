package com.nadafeteiha.budgetwise.ui.screen.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.util.toDoubleOrZero


@Composable
fun WelcomeDialog(
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
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Enter Name and Age") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = name,
                    onValueChange = { onNameChange(it) },
                    label = { Text("Name") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = "$budget",
                    onValueChange = { onBudgetChange(it.toDoubleOrZero()) },
                    label = { Text(stringResource(id = R.string.budget)) },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onDismiss() }
            ) {
                Text(stringResource(id = R.string.save))
            }
        }
    )
}
