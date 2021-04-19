package dev.gonz.compose.experiments.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import dev.gonz.compose.experiments.components.PreviewWrapper

@Composable
fun SplashScreen(navController: NavController? = null) {

    val routes = mapOf(
        "Polygon" to Routes.POLYGON,
        "Pie Chart" to Routes.PIE_CHART,
        "Wizeline Logo" to Routes.WIZE_LOGO,
    )

    Scaffold(
        topBar = {
            TopAppBar(elevation = 5.dp, backgroundColor = Color.White) {
                Text(text = "Compose Experiments", color = Color.Black)
            }
        },
    ) {
        LazyColumn {
            itemsIndexed(routes.entries.toList()) { index, item ->
                Column(modifier = Modifier.clickable {
                    navController?.navigate(item.value)
                }) {
                    Text(
                        text = item.key,
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 20.dp)
                    )
                    Divider(color = Color(0x204d4d4d))
                }
            }
        }
    }
}

@Composable
@Preview
fun Preview() {
    PreviewWrapper {
        SplashScreen()
    }
}