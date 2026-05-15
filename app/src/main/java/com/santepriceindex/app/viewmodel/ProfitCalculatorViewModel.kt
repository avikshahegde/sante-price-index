package com.santepriceindex.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfitCalculatorUiState(
    val mandiPrice: String = "",
    val transportCost: String = "",
    val wastagePercent: String = "5",
    val profitMarginPercent: String = "20",
    val recommendedPrice: Double = 0.0,
    val totalCostPerQuintal: Double = 0.0,
    val grossSales: Double = 0.0,
    val netProfit: Double = 0.0
)

class ProfitCalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfitCalculatorUiState())
    val uiState: StateFlow<ProfitCalculatorUiState> = _uiState.asStateFlow()

    fun onMandiPriceChange(value: String) {
        _uiState.update { it.copy(mandiPrice = value) }
    }

    fun onTransportCostChange(value: String) {
        _uiState.update { it.copy(transportCost = value) }
    }

    fun onWastageChange(value: String) {
        _uiState.update { it.copy(wastagePercent = value) }
    }

    fun onProfitMarginChange(value: String) {
        _uiState.update { it.copy(profitMarginPercent = value) }
    }

    fun calculate() {
        val state = _uiState.value
        val purchasePrice = state.mandiPrice.toDoubleOrNull() ?: 0.0
        val transport = state.transportCost.toDoubleOrNull() ?: 0.0
        val wastage = state.wastagePercent.toDoubleOrNull() ?: 0.0
        val margin = state.profitMarginPercent.toDoubleOrNull() ?: 0.0

        if (purchasePrice > 0) {
            val totalCost = purchasePrice + transport
            val sellableWeight = 100.0 * (1 - (wastage / 100.0))
            val costPricePerKg = totalCost / sellableWeight
            val retailPricePerKg = costPricePerKg * (1 + (margin / 100.0))
            
            val grossRevenue = retailPricePerKg * sellableWeight
            val profit = grossRevenue - totalCost
            
            _uiState.update { 
                it.copy(
                    totalCostPerQuintal = totalCost,
                    recommendedPrice = retailPricePerKg,
                    grossSales = grossRevenue,
                    netProfit = profit
                )
            }
        }
    }
}
