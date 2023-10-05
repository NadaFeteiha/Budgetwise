package com.nadafeteiha.budgetwise.ui.screen.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.ui.composable.VerticalDivider
import com.nadafeteiha.budgetwise.ui.screen.home.CategoryUIState
import com.nadafeteiha.budgetwise.ui.screen.home.HomeUIState
import com.nadafeteiha.budgetwise.ui.theme.BudgetwiseTheme

@Composable
fun BudgetCard(
    modifier: Modifier = Modifier,
    state: HomeUIState
) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BudgetTextValueItem(title = "Spent", value = state.totalSpent)
            VerticalDivider(modifier = Modifier.height(32.dp))
            BudgetTextValueItem(title = "Available", value = state.totalSpent)
            VerticalDivider(modifier = Modifier.height(32.dp))
            BudgetTextValueItem(title = "Budget", value = state.totalSpent)
        }

        MultiColoredProgressBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            progressColors = state.categories.map { Color(it.color) },
            progressValues = state.categories.map { it.progress },
        )

        state.categories.forEach {
            CategoryItem(
                modifier.padding(vertical = 8.dp),
                category = it
            )
        }
    }
}

@Composable
private fun BudgetTextValueItem(
    modifier: Modifier = Modifier,
    title: String,
    value: Double
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Text(text = "$$value")
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
                        balance = 100.0,
                        spent = 10.0,
                        color = 0xFF00FF00
                    ),
                    CategoryUIState(
                        icon = R.drawable.home_icon,
                        title = "Food",
                        balance = 100.0,
                        spent = 30.0,
                        color = 0xFF6970C9
                    ),
                    CategoryUIState(
                        icon = R.drawable.home_icon,
                        title = "Food",
                        balance = 100.0,
                        spent = 20.0,
                        color = 0xFF53BD71
                    )
                )
            )
        )
    }
}
