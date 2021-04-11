package dev.gonz.compose.experiments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gonz.compose.experiments.ui.theme.ComposeExperimentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExperimentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var sliderValue by remember {
        mutableStateOf(1F)
    }

    Column(modifier = Modifier.fillMaxSize(),
        Arrangement.SpaceEvenly,
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
    ComposeExperimentsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PieChart()
        }
    }
}