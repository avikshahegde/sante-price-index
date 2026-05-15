package com.santepriceindex.app.ui.screens.pricewatch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.santepriceindex.app.data.model.MandiPrice
import com.santepriceindex.app.data.repository.MockPriceRepository
import com.santepriceindex.app.ui.components.MarketCard
import com.santepriceindex.app.ui.components.MarketTextField
import com.santepriceindex.app.ui.theme.MarketYellow
import com.santepriceindex.app.viewmodel.PriceWatchViewModel

@Composable
fun PriceWatchScreen(
    viewModel: PriceWatchViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(
                modelClass: Class<T>
            ): T {

                return PriceWatchViewModel(
                    MockPriceRepository()
                ) as T
            }
        }
    )
) {

    val uiState by viewModel.uiState.collectAsState()

    val filteredPrices = uiState.prices.filter {

        it.crop.contains(
            uiState.searchQuery,
            ignoreCase = true
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Price Watch",
            style = MaterialTheme.typography.displayLarge,
            color = MarketYellow,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        MarketTextField(
            value = uiState.searchQuery,
            onValueChange = {
                viewModel.onSearchQueryChange(it)
            },
            label = "Search Crops",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.isLoading) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    color = MarketYellow
                )
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(filteredPrices) { price ->

                    PriceItem(price)
                }
            }
        }
    }
}

@Composable
fun PriceItem(price: MandiPrice) {

    val flashColor by animateColorAsState(
        targetValue = MarketYellow,
        animationSpec = tween(durationMillis = 300),
        label = "PriceFlash"
    )

    MarketCard {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = price.crop,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = price.market,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "₹${String.format("%.2f", price.price)}",
                    color = flashColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = "per ${price.unit}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}