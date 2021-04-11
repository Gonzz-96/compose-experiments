package dev.gonz.compose.experiments.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun VariableNumberOfSidesPolygon(
    modifier: Modifier = Modifier,
    numberOfSides: Int = 3,
) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2
        val path = Path().apply {
            moveTo(size.width / 2, 0F)
        }
        var startAngle = PI / 2 // from the top of the canvas
        numberOfSides.times {
            val slideAngle = (2 * PI) / numberOfSides

            val y = radius * sin(startAngle + slideAngle)
            val x = radius * cos(startAngle + slideAngle)

            // radius must be added since Path (unlike DrawScope.drawLine)
            // use absolute coordinate instead
            path.lineTo(x.toFloat() + radius, - y.toFloat() + radius)
            startAngle += slideAngle
        }

        drawPath(path, Color.Blue, style = Stroke(10.0F))
    }
}

inline fun Int.times(block: (Int) -> Unit) {
    for (i in 0 until this) {
        block(i)
    }
}