package dev.gonz.compose.experiments.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gonz.compose.experiments.components.PreviewWrapper
import dev.gonz.compose.experiments.components.VariableNumberOfSidesPolygon

@Composable
fun VariableNumberOfSidesPolygonScreen() {
    var numberOfSides by remember {
        mutableStateOf(1)
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VariableNumberOfSidesPolygon(
            numberOfSides = numberOfSides,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5F)
        )
        Slider(
            steps = 1,
            valueRange = 3F..30F,
            modifier = Modifier.padding(horizontal = 20.dp),
            value = numberOfSides.toFloat(),
            onValueChange = {
                numberOfSides = it.toInt()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PolygonPreview() {
    PreviewWrapper {
        VariableNumberOfSidesPolygonScreen()
    }
}