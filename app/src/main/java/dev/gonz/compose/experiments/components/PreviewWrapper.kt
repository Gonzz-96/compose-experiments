package dev.gonz.compose.experiments.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.gonz.compose.experiments.ui.theme.ComposeExperimentsTheme

@Composable
fun PreviewWrapper(Content: @Composable () -> Unit) {
    ComposeExperimentsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Content()
        }
    }
}