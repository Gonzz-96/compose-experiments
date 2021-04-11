package dev.gonz.compose.experiments.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun PieChart(circleProportion: Float = 1F) {
    val proportions = listOf(0.15F, 0.30F, 0.40F, 0.15F)
    val colors = listOf(Color.Blue, Color.Yellow, Color.Red, Color.Black)
    val separator = 2F

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.5F)
    ) {
        val shift = 30
        var startAngle = -90F + shift
        proportions.forEachIndexed { index, value ->
            val sweep = (360F * value) * circleProportion
            drawArc(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = sweep - separator,
                useCenter = true,
                size = size.minDimension.run {
                    Size(this, this)
                }
            )

            startAngle += (sweep + separator)
        }
    }
}