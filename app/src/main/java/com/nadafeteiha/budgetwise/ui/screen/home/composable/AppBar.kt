package com.nadafeteiha.budgetwise.ui.screen.home.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun AppBar() {
    Row {
        Text(
            text = " Monthly Budget",
            maxLines = 1,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp)
        )

        Icon(
            imageVector = Icons.Outlined.ArrowDropDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Outlined.Share,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )

    }
}
