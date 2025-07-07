package com.example.pesankaos.ui.theme.components

import androidx.compose.runtime.State
import androidx.core.location.LocationRequestCompat.Quality
import androidx.lifecycle.ViewModel
import com.example.pesankaos.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat

class OrderViewModel : ViewModel {
    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    private fun calculatePrice(
        quality: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatePrice = quality * PRICE_PER_KAOS
        if (pickupOptions()[0]) == pickupDate) {
            calculatePrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        val indonesia = locale(languate:"in",country:"10")
        
    }

    private fun pickupOptions(): List<String>{
        val dateOption = mutableListOf()<String>()
        val formatter = SimpleDateFormat(pattern : "E MM d", locale.getDefault())

    }

}