package com.santepriceindex.app.ui.screens.trends

import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.santepriceindex.app.data.model.TrendEntry
import com.santepriceindex.app.data.repository.MockTrendRepository
import com.santepriceindex.app.ui.components.MarketCard
import com.santepriceindex.app.ui.components.SectionHeader
import com.santepriceindex.app.ui.theme.MarketYellow
import com.santepriceindex.app.viewmodel.TrendsViewModel

@Composable
fun TrendsScreen(
    viewModel: TrendsViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TrendsViewModel(MockTrendRepository()) as T
            }
        }
    )
) {

    val uiState = viewModel.uiState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Market Trends",
            style = MaterialTheme.typography.displayLarge,
            color = MarketYellow,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.trends.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MarketYellow)
            }

        } else {

            MarketCard {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Tomato Price Trend (7 Days)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    TrendChart(
                        uiState.trends.map {
                            TrendPoint(
                                it.date,
                                it.price
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Min: ₹${uiState.trends.minOf { it.price }.toInt()}",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "Max: ₹${uiState.trends.maxOf { it.price }.toInt()}",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader(title = "Insights")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {

                MarketCard {

                    Text(
                        text = "Price increased by 38% this week due to supply shortages in neighboring markets.",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 14.sp
                    )
                }
            }

            item {

                MarketCard {

                    Text(
                        text = "Next week projection: Stable prices expected as new harvest arrivals begin.",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TrendChart(data: List<TrendPoint>) {

    val maxPrice = data.maxOf { it.price }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {

        data.forEach { point ->

            val heightPercent = (point.price / maxPrice).toFloat()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "${point.price.toInt()}",
                    fontSize = 10.sp,
                    color = MarketYellow,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .fillMaxHeight(heightPercent)
                        .background(
                            color = if (point.price == maxPrice)
                                MarketYellow
                            else
                                Color.DarkGray,
                            shape = RoundedCornerShape(
                                topStart = 4.dp,
                                topEnd = 4.dp
                            )
                        )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = point.day,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

data class TrendPoint(
    val day: String,
    val price: Double
)