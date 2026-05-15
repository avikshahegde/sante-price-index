package com.santepriceindex.app.ui.screens.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santepriceindex.app.ui.theme.MarketYellow

@Composable
fun DigitalPriceBoardScreen(onBack: () -> Unit) {

    val displayPrices = listOf(
        BoardPrice("Tomato", 35.0, "KG"),
        BoardPrice("Potato", 22.0, "KG"),
        BoardPrice("Onion", 28.0, "KG"),
        BoardPrice("Chilli", 12.0, "100g")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Text(
            text = "MARKET RATE",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MarketYellow,
            fontWeight = FontWeight.Black,
            fontSize = 24.sp,
            letterSpacing = 8.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            color = MarketYellow,
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(displayPrices) { price ->
                BoardItem(price)
            }

            item {

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "SANTE PRICE INDEX",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                    fontSize = 12.sp,
                    letterSpacing = 4.sp
                )
            }
        }
    }
}

@Composable
fun BoardItem(price: BoardPrice) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MarketYellow,
                shape = RoundedCornerShape(4.dp)
            )
            .background(Color.Black)
            .padding(20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = price.crop.uppercase(),
                fontWeight = FontWeight.Black,
                color = Color.White,
                fontSize = 32.sp
            )

            Row(
                verticalAlignment = Alignment.Bottom
            ) {

                Text(
                    text = "₹${price.price.toInt()}",
                    color = MarketYellow,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = "/${price.unit}",
                    color = MarketYellow,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        bottom = 12.dp,
                        start = 4.dp
                    )
                )
            }
        }
    }
}

data class BoardPrice(
    val crop: String,
    val price: Double,
    val unit: String
)