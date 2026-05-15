package com.santepriceindex.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object PriceWatch : Screen("price_watch", "Price Watch", Icons.Default.Visibility)
    object ProfitCalc : Screen("profit_calc", "Profit Calc", Icons.Default.Calculate)
    object PriceBoard : Screen("price_board", "Price Board", Icons.Default.Dashboard)
    object Trends : Screen("trends", "Trends", Icons.Default.ShowChart)
}

val navItems = listOf(
    Screen.PriceWatch,
    Screen.ProfitCalc,
    Screen.PriceBoard,
    Screen.Trends
)
