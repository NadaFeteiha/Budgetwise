package com.nadafeteiha.budgetwise.ui.screen.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.ui.screen.home.CategoryUIState
import com.nadafeteiha.budgetwise.ui.screen.home.HomeInteractions
import com.nadafeteiha.budgetwise.ui.screen.home.getRemainderAmountCanSpend
import com.nadafeteiha.budgetwise.util.toDoubleOrZero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    categories: List<CategoryUIState>,
    selectedCategory: CategoryUIState?,
    selectedSpendValue: Double,
    isValidAmountToSpend: Boolean,
    listener: HomeInteractions
) {
    ModalBottomSheet(
        onDismissRequest = { listener.onUpdateBottomSheetVisibility(false) },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (selectedCategory == null) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = stringResource(id = R.string.select_save),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                LazyColumn {
                    items(categories) { category ->
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { listener.onCategorySelected(category) }
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
                    spendValue = selectedSpendValue,
                    isValidAmountToSpend = isValidAmountToSpend,
                    onValueChange = listener::onSpentValueChange,
                    onDoneClicked = listener::onDoneClicked,
                    onCancelClicked = listener::onUpdateBottomSheetVisibility,
                    title = selectedCategory.title
                )
            }
        }
    }
}

@Composable
private fun SpendAmount(
    modifier: Modifier = Modifier,
    title: String,
    spendValue: Double,
    isValidAmountToSpend: Boolean,
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
            isError = !isValidAmountToSpend,
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
                enabled = isValidAmountToSpend && spendValue > 0.0,
                shape = RoundedCornerShape(4.dp),
            ) {
                Text(
                    stringResource(id = R.string.save), modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
