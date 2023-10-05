package com.nadafeteiha.budgetwise.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    thickness: Int = 1
) {
    Spacer(
        modifier = modifier
            .width(thickness.dp)
            .fillMaxHeight()
            .background(color)
    )
}
