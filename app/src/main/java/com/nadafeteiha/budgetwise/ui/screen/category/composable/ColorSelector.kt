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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorSelector(
    modifier: Modifier = Modifier,
    colors: List<Long>,
    onColorSelected: (Long) -> Unit,
    selectedColor: Long?
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(colors) { color ->
            val isSelected = color == selectedColor

            Box(modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp)
                .background(
                    color = Color(color.toInt()), shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 2.dp,
                    color = if (isSelected) Color.Gray else Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 4.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.background else Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable { onColorSelected(color) })
        }
    }
}
