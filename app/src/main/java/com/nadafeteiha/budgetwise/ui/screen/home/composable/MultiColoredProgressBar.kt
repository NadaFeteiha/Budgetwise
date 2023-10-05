package com.nadafeteiha.budgetwise.ui.screen.home.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MultiColoredProgressBar(
    modifier: Modifier = Modifier,
    progressValues: List<Float>,
    progressColors: List<Color>,
    backgroundColor: Color = Color.Gray,
    height: Dp = 32.dp,
) {
    Box(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(shape = RoundedCornerShape(10.dp))
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {

            drawRect(color = backgroundColor, size = size)

            var startX = 0f
            val totalWidth = size.width

            for ((index, progressValue) in progressValues.withIndex()) {
                val segmentWidth = totalWidth * progressValue
                drawRect(
                    color = progressColors[index],
                    size = Size(segmentWidth, size.height),
                    topLeft = Offset(startX, 0f)
                )
                startX += segmentWidth
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MultiColoredProgressBarDemo() {
    val progressValues = listOf(0.2f, 0.3f, 0.4f)
    val progressColors = listOf(Color.Red, Color.Green, Color.Blue)
    val backgroundColor = Color.Gray

    MultiColoredProgressBar(
        progressValues = progressValues,
        progressColors = progressColors,
        backgroundColor = backgroundColor,
        modifier = Modifier.fillMaxWidth()
    )
}
