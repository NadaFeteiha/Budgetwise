package com.nadafeteiha.budgetwise.ui.screen.home.composable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.ui.screen.home.CategoryUIState
import com.nadafeteiha.budgetwise.ui.screen.home.getRemainderAmountCanSpend
import com.nadafeteiha.budgetwise.ui.theme.BudgetwiseTheme
import com.nadafeteiha.budgetwise.ui.theme.Green

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: CategoryUIState,
    animationDurationMillis: Int = 3000
) {
    val animatedProgress = remember { Animatable(0f) }

    val defaultStyle = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    val spentText = buildAnnotatedString {
        withStyle(style = defaultStyle) { append("spent ") }
        withStyle(style = defaultStyle.copy(color = Color.Red)) { append("$${category.spent} ") }
        withStyle(style = defaultStyle) { append("of $${category.budget}") }
    }

    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = category.icon),
                contentDescription = category.title,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .padding(end = 12.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(category.color))
                    .padding(8.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(text = spentText)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$${category.getRemainderAmountCanSpend()}",
                        color = Green,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "left",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp)),
            progress = animatedProgress.value,
            color = Color(category.color),
            trackColor = MaterialTheme.colorScheme.tertiary
        )
    }

    LaunchedEffect(category.progress) {
        animatedProgress.animateTo(
            targetValue = category.progress,
            animationSpec = tween(durationMillis = animationDurationMillis)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    BudgetwiseTheme {
        CategoryItem(
            category = CategoryUIState(
                icon = R.drawable.home_icon,
                title = "Food",
                budget = 100.0,
                spent = 10.0,
                color = 0xFF00FF00
            )
        )
    }
}

