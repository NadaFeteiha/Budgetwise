package com.nadafeteiha.budgetwise.ui.screen.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.ui.screen.category.composable.ColorSelector
import com.nadafeteiha.budgetwise.ui.screen.category.composable.IconSelector
import com.nadafeteiha.budgetwise.ui.theme.BudgetwiseTheme
import com.nadafeteiha.budgetwise.ui.theme.LocalNavController
import com.nadafeteiha.budgetwise.util.toDoubleOrZero
import com.nadafeteiha.budgetwise.util.toStringOrEmpty

@Composable
fun CategoryScreen(viewModel: CategoryViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    CategoryContent(category = state, listener = viewModel)
    LaunchedEffect(key1 = state.isSuccessfullySaved) {
        if (state.isSuccessfullySaved) {
            navController.popBackStack()
        }
    }
}

@Composable
private fun CategoryContent(
    category: CategoryUIState,
    listener: CategoryInteractions,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            value = category.name,
            onValueChange = listener::onTitleChanged,
            label = { Text("Name") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            placeholder = { Text("Enter your name") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = category.total.toStringOrEmpty(),
            onValueChange = {
                listener.onBudgetChanged(it.toDoubleOrZero())
            },
            label = { Text("Total") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
            placeholder = { Text("Total") }
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Color",
            style = MaterialTheme.typography.titleMedium
        )
        ColorSelector(
            colors = category.colors,
            onColorSelected = listener::onColorSelected,
            selectedColor = category.selectedColor
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Icon",
            style = MaterialTheme.typography.titleMedium
        )
        IconSelector(
            icons = category.icons,
            onIconSelected = listener::onIconSelected,
            selectedIcon = category.selectedIcon
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = listener::onSaveClicked,
            enabled = category.isAllDataSet(),
            shape = RoundedCornerShape(4.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.save)
            )
            Text(stringResource(id = R.string.save), modifier = Modifier.padding(start = 8.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CategoryContentPreview() {
    BudgetwiseTheme {
        CategoryScreen()
    }
}
