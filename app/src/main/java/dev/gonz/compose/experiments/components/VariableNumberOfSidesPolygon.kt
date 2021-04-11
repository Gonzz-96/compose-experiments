package dev.gonz.compose.experiments.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
        var startAngle = PI / 2 // from the top of the canvas
        val path = Path().apply {
            val offset = getNewCoordinatesFrom(startAngle.toFloat(), radius)
            moveTo(offset.x, offset.y)
        }
        repeat(numberOfSides) {
            val slideAngle = (2 * PI) / numberOfSides
            val offset = getNewCoordinatesFrom((startAngle + slideAngle).toFloat(), radius)

            path.lineTo(offset.x, offset.y)
            startAngle += slideAngle
        }
        drawPath(path, Color.Blue.copy(alpha = 0.5F), style = Stroke(10.0F))
    }
}

fun getNewCoordinatesFrom(rad: Float, r: Float): Offset {
    val y = r * sin(rad)
    val x = r * cos(rad)
    // radius must be added since Path (unlike DrawScope.drawLine)
    // use absolute coordinate instead
    return Offset(x + r, - y + r)
}

inline fun Int.times(block: (Int) -> Unit) {
    for (i in 0 until this) {
        block(i)
    }
}