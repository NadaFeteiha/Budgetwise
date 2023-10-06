package com.nadafeteiha.budgetwise.ui.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.ui.composable.VerticalDivider
import com.nadafeteiha.budgetwise.ui.screen.home.CategoryUIState
import com.nadafeteiha.budgetwise.ui.screen.home.HomeUIState
import com.nadafeteiha.budgetwise.ui.theme.BudgetwiseTheme

@Composable
fun BudgetCard(
    modifier: Modifier = Modifier, state: HomeUIState
) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MonthSelector(
            modifier = Modifier.padding(vertical = 16.dp),
            date = state.date
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BudgetTextValueItem(
                title = stringResource(id = R.string.spent), value = state.totalSpent
            )
            VerticalDivider(modifier = Modifier.height(32.dp))
            BudgetTextValueItem(
                title = stringResource(id = R.string.available),
                value = state.budget - state.totalSpent,
                valueColor = MaterialTheme.colorScheme.secondary
            )
            VerticalDivider(modifier = Modifier.height(32.dp))
            BudgetTextValueItem(title = stringResource(id = R.string.budget), value = state.budget)
        }

        MultiColoredProgressBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            progressColors = state.categories.map { Color(it.color) },
            progressValues = state.categories.map { (it.spent/state.budget).toFloat() },
        )

        LazyColumn {
            items(state.categories) {
                CategoryItem(
                    modifier.padding(vertical = 8.dp), category = it
                )
            }
        }
    }
}

@Composable
private fun BudgetTextValueItem(
    modifier: Modifier = Modifier, title: String, value: Double, valueColor: Color = Color.Black
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.tertiary
        )
        Text(
            text = "$$value",
            style = MaterialTheme.typography.titleLarge,
            color = valueColor
        )
    }
}


@Composable
fun MonthSelector(
    modifier: Modifier = Modifier,
    date: String
) {

    Row(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(5.dp),
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
            )
            .padding(6.dp),
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetPreview() {
    BudgetwiseTheme {
        BudgetCard(
            state = HomeUIState(
                categories = listOf(
                    CategoryUIState(
                        icon = R.drawable.home_icon,
                        title = "Food",
                        budget = 100.0,
                        spent = 10.0,
                        color = 0xFF00FF00
                    ), CategoryUIState(
                        icon = R.drawable.home_icon,
                        title = "Food",
                        budget = 100.0,
                        spent = 30.0,
                        color = 0xFF6970C9
                    ), CategoryUIState(
                        icon = R.drawable.home_icon,
                        title = "Food",
                        budget = 100.0,
                        spent = 20.0,
                        color = 0xFF53BD71
                    )
                )
            )
        )
    }
}
