package dev.gonz.compose.experiments.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val SPLASH = "SplashScreen"
    const val PIE_CHART = "PieChartScreen"
    const val POLYGON = "VariableNumberOfSidesPolygonScreen"
    const val WIZE_LOGO = "WizelineLogoScreen"
}

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) { SplashScreen(navController = navController) }
        composable(Routes.POLYGON) { VariableNumberOfSidesPolygonScreen() }
        composable(Routes.PIE_CHART) { PieChartScreen() }
        composable(Routes.WIZE_LOGO) { WizelineLogoScreen() }
    }
}