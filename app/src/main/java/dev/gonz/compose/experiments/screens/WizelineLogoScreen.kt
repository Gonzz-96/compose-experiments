package dev.gonz.compose.experiments.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import dev.gonz.compose.experiments.components.PreviewWrapper

val WIZELINE_COLOR = Color(0xFFEC3C42)

@Composable
fun WizelineLogoScreen() {
    val currentState = remember {
        MutableTransitionState(initialState = true)
            .apply { targetState = false }
    }

    val transition = updateTransition(currentState, "Current State")

    // introductory line animation progress
    val introductoryLineFadeInProgress by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 1_000, delayMillis = 0)
        }, label = "Introductory Line Progress"
    ) { isStarting -> if (isStarting) 0F else 1F }

    // introductory line animation progress
    val introductoryLineFadeOutProgress by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 700, delayMillis = 500)
        }, label = "Introductory Line Progress"
    ) { isStarting -> if (isStarting) 0F else 1F }

    // circle animation progress
    val circleProgress by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 1_000,
                delayMillis = 800
            )
        }, label = "Circle Progress"
    ) { isStarting -> if (isStarting) 0F else 1F }

    // intermediate line progress
    val intermediateLineProgress by transition.animateFloat(
        transitionSpec = {
          tween(
              durationMillis = 1_000,
              delayMillis = 2_000,
          )
        },
        label = "Intermediate Line Progress"
    ) { isStarting -> if (isStarting) 0F else 1F }

    Box(modifier = Modifier.fillMaxSize().background(WIZELINE_COLOR)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val r = size.minDimension * 0.35F

            val introductoryLine = Path()
            introductoryLine.moveTo(center.x + r, size.height)
            introductoryLine.relativeLineTo(0F, - size.height / 2)

            val circlePath = Path()
            circlePath.addArc(
                Rect(center = center, r),
                startAngleDegrees = 0F,
                sweepAngleDegrees = -360F
            )

            val intermediatePath = Path()
            intermediatePath.moveTo(center.x - r, size.height / 2)
            intermediatePath.relativeLineTo((3F / 5F) * r, 0F)
            intermediatePath.relativeLineTo((1F / 5F) * r, (2F / 5F) * r)
            intermediatePath.relativeLineTo((2F / 5F) * r, - 2F * (2F / 5F) * r)
            intermediatePath.relativeLineTo((1F / 5F) * r, (2F / 5F) * r)
            intermediatePath.relativeLineTo((3F / 5F) * r, 0F)

            drawPath(
                circlePath.getSegment(to = circleProgress),
                Color.White,
                style = Stroke(50F, cap = StrokeCap.Round)
            )
            drawPath(
                introductoryLine.getSegment(
                    from = introductoryLineFadeOutProgress,
                    to = introductoryLineFadeInProgress
                ),
                Color.White,
                style = Stroke(50F, cap = StrokeCap.Round)
            )
            drawPath(
                intermediatePath.getSegment(to = intermediateLineProgress),
                Color.White,
                style = Stroke(50F, cap = StrokeCap.Round)
            )
        }
    }
}

fun Path.getSegment(
    from: Float = 0F,
    to: Float = 1F,
    forceClose: Boolean = false
): Path = PathMeasure().let { pathMeasure ->
    val newPath = Path()
    pathMeasure.setPath(this, forceClose)
    pathMeasure.getSegment(
        pathMeasure.length * from,
        pathMeasure.length * to,
        newPath
    )
    newPath
}


@Composable
@Preview(showBackground = true)
fun WizelineLogoScreenPreview() {
    PreviewWrapper {
        WizelineLogoScreen()
    }
}