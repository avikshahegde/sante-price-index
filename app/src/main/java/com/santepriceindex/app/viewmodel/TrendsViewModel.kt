package com.santepriceindex.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santepriceindex.app.data.model.TrendEntry
import com.santepriceindex.app.data.repository.TrendRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class TrendsUiState(
    val isLoading: Boolean = false,
    val trends: List<TrendEntry> = emptyList(),
    val error: String? = null,
    val selectedCrop: String = "Tomato"
)

class TrendsViewModel(
    private val repository: TrendRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TrendsUiState())
    val uiState: StateFlow<TrendsUiState> = _uiState.asStateFlow()

    init {
        loadTrends(_uiState.value.selectedCrop)
    }

    fun loadTrends(crop: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, selectedCrop = crop) }
            repository.getTrends(crop)
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { trends ->
                    _uiState.update { it.copy(isLoading = false, trends = trends) }
                }
        }
    }
}
