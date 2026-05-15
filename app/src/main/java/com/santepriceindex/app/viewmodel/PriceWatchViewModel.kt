package com.santepriceindex.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santepriceindex.app.data.model.MandiPrice
import com.santepriceindex.app.data.repository.PriceRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class PriceWatchUiState(
    val isLoading: Boolean = false,
    val prices: List<MandiPrice> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)

class PriceWatchViewModel(
    private val repository: PriceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PriceWatchUiState())
    val uiState: StateFlow<PriceWatchUiState> = _uiState.asStateFlow()

    init {
        loadPrices()
    }

    fun loadPrices() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getMandiPrices()
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { prices ->
                    _uiState.update { it.copy(isLoading = false, prices = prices) }
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }
}
