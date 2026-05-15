package com.santepriceindex.app.ui.screens.profit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.santepriceindex.app.ui.components.MarketButton
import com.santepriceindex.app.ui.components.MarketCard
import com.santepriceindex.app.ui.components.MarketTextField
import com.santepriceindex.app.ui.theme.MarketYellow
import com.santepriceindex.app.viewmodel.ProfitCalculatorViewModel

@Composable
fun ProfitCalculatorScreen(
    viewModel: ProfitCalculatorViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        Text(
            text = "Profit Calculator",
            style = MaterialTheme.typography.displayLarge,
            color = MarketYellow,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        MarketCard(
            modifier = Modifier.padding(bottom = 16.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Input Costs (per Quintal)",
                    fontWeight = FontWeight.Bold,
                    color = MarketYellow,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                MarketTextField(
                    value = uiState.mandiPrice,
                    onValueChange = {
                        viewModel.onMandiPriceChange(it)
                    },
                    label = "Mandi Purchase Price (₹)",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                MarketTextField(
                    value = uiState.transportCost,
                    onValueChange = {
                        viewModel.onTransportCostChange(it)
                    },
                    label = "Transport & Loading (₹)",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }

        MarketCard(
            modifier = Modifier.padding(bottom = 24.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Margins & Wastage",
                    fontWeight = FontWeight.Bold,
                    color = MarketYellow,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                MarketTextField(
                    value = uiState.wastagePercent,
                    onValueChange = {
                        viewModel.onWastageChange(it)
                    },
                    label = "Estimated Wastage (%)",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                MarketTextField(
                    value = uiState.profitMarginPercent,
                    onValueChange = {
                        viewModel.onProfitMarginChange(it)
                    },
                    label = "Profit Margin (%)",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }

        MarketButton(
            text = "Calculate Retail Price",
            onClick = {
                viewModel.calculate()
            }
        )

        if (uiState.recommendedPrice > 0) {

            Spacer(modifier = Modifier.height(32.dp))

            MarketCard {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Suggested Selling Price",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "₹${String.format("%.2f", uiState.recommendedPrice)} / KG",
                        color = MarketYellow,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FinancialMetricRow(
                        label = "Total Cost",
                        value = uiState.totalCostPerQuintal
                    )

                    FinancialMetricRow(
                        label = "Gross Sales",
                        value = uiState.grossSales
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.DarkGray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp),

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "NET PROFIT",
                            color = MarketYellow,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Text(
                            text = "₹${String.format("%.2f", uiState.netProfit)}",
                            color = MarketYellow,
                            fontWeight = FontWeight.Black,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "*Net Profit is what stays in your pocket after all expenses.",
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun FinancialMetricRow(
    label: String,
    value: Double
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )

        Text(
            text = "₹${String.format("%.2f", value)}",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}