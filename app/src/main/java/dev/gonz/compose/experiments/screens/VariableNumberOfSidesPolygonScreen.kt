package dev.gonz.compose.experiments.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gonz.compose.experiments.components.PreviewWrapper
import dev.gonz.compose.experiments.components.VariableNumberOfSidesPolygon

@Composable
fun VariableNumberOfSidesPolygonScreen() {
    var numberOfSides by remember { mutableStateOf(3) }
    var pathProgress by remember { mutableStateOf(1F) }

    SideEffect {
        Log.v("Polygon", "Recomposed!")
    }


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VariableNumberOfSidesPolygon(
            numberOfSides = numberOfSides,
            progress = pathProgress,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5F)
        )

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (numberOfSides > 3) numberOfSides--
            }) {
                Text("-")
            }
            Text(numberOfSides.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.width(96.dp).clickable {
                    numberOfSides = 3
                }
            )
            Button(onClick = { numberOfSides++ }) {
                Text("+")
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(value = pathProgress, onValueChange = { pathProgress = it })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PolygonPreview() {
    PreviewWrapper {
        VariableNumberOfSidesPolygonScreen()
    }
}