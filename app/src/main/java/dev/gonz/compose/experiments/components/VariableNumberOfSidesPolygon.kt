package dev.gonz.compose.experiments.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun VariableNumberOfSidesPolygon(
    modifier: Modifier = Modifier,
    numberOfSides: Int = 3,
    progress: Float = 1F,
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

        val newPath = Path()
        val measure = PathMeasure()

        measure.setPath(path, true)
        measure.getSegment(0F, measure.length * progress, newPath, true)

        drawPath(newPath, Color.Blue.copy(alpha = 0.5F), style = Stroke(10F))
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