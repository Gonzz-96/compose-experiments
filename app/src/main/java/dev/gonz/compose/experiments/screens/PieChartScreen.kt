package dev.gonz.compose.experiments.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gonz.compose.experiments.components.PieChart
import dev.gonz.compose.experiments.components.PreviewWrapper

@Composable
fun PieChartScreen() {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PreviewWrapper {
        PieChartScreen()
    }
}