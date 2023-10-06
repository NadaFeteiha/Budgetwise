package com.nadafeteiha.budgetwise.ui.screen.category.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconSelector(
    modifier: Modifier = Modifier,
    icons: List<Int>,
    onIconSelected: (Int) -> Unit,
    selectedIcon: Int?
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(icons) { icon ->
            val isSelected = icon == selectedIcon

            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        width = 2.dp,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                        shape = CircleShape
                    )
                    .clickable { onIconSelected(icon) }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(36.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = if (isSelected) Color.White else MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}
