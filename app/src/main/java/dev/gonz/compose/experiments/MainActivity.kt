package dev.gonz.compose.experiments

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gonz.compose.experiments.ui.theme.ComposeExperimentsTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExperimentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    VariableNumberOfSidesPolygon(numberOfSides = 5)
                }
            }
        }
    }
}

@Composable
fun PreviewWrapper(Content: @Composable () -> Unit) {
    ComposeExperimentsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Content()
        }
    }
}

@Composable
fun PieChartProgress() {
    var sliderValue by remember {
        mutableStateOf(1F)
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PieChart(circleProportion = sliderValue)
        Slider(
            modifier = Modifier.padding(horizontal = 20.dp),
            value = sliderValue,
            onValueChange = {
                sliderValue = it
            }
        )
    }
}

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PreviewWrapper {
        PieChartProgress()
    }
}

@Composable
fun VariableNumberOfSidesPolygon(numberOfSides: Int = 3) {

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(fraction = 0.5F)
    ) {
        val radius = size.minDimension / 2
        var startAngle = 0.0 // from the top of the canvas
        numberOfSides.times {
            val startY = radius * sin(startAngle)
            val startX = radius * cos(startAngle)

            val slideAngle = 2 * PI / numberOfSides

            val endY = radius * sin(startAngle + slideAngle)
            val endX = radius * cos(startAngle + slideAngle)

            Log.v("CIRCLE", startAngle.toString())

            drawLine(
                color = Color.Blue,
                start = Offset(startX.toFloat(), startY.toFloat())  + offsetFrom(radius),
                end = Offset(endX.toFloat(), endY.toFloat()) + offsetFrom(radius)
            )

            startAngle += slideAngle
        }
    }
}

inline fun Int.times(block: (Int) -> Unit) {
    for (i in 0 until this) {
        block(i)
    }
}

fun offsetFrom(value: Float) = Offset(value, value)

@Preview(showBackground = true)
@Composable
fun PolygonPreview() {
    PreviewWrapper {
        VariableNumberOfSidesPolygon(numberOfSides = 10)
    }
}