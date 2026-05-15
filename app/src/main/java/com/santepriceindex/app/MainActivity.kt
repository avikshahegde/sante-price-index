package com.santepriceindex.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.santepriceindex.app.ui.navigation.Screen
import com.santepriceindex.app.ui.navigation.navItems
import com.santepriceindex.app.ui.screens.board.DigitalPriceBoardScreen
import com.santepriceindex.app.ui.screens.pricewatch.PriceWatchScreen
import com.santepriceindex.app.ui.screens.profit.ProfitCalculatorScreen
import com.santepriceindex.app.ui.screens.trends.TrendsScreen
import com.santepriceindex.app.ui.theme.MarketYellow
import com.santepriceindex.app.ui.theme.SantePriceIndexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SantePriceIndexTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
                contentColor = MarketYellow
            ) {
                navItems.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MarketYellow,
                            selectedTextColor = MarketYellow,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.PriceWatch.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.PriceWatch.route) {
                PriceWatchScreen()
            }
            composable(Screen.ProfitCalc.route) {
                ProfitCalculatorScreen()
            }
            composable(Screen.PriceBoard.route) {
                DigitalPriceBoardScreen(onBack = { /* Not needed in main nav */ })
            }
            composable(Screen.Trends.route) {
                TrendsScreen()
            }
        }
    }
}
