package com.example.pesankaos.ui.theme.components
import androidx.lifecycle.ViewModel
import com.example.pesankaos.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val PRICE_PER_KAOS = 30000
private const val PRICE_FOR_SAME_DAY_PICKUP = 5000
class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions =
    pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()
    fun setQuantity(jumlahKaos: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = jumlahKaos,
                price = calculatePrice(quantity = jumlahKaos)
            )
        }
    }
    fun setColor(warnaKaos: String) {
        _uiState.update { currentState ->
            currentState.copy(color = warnaKaos)
        }
    }
    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }
    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_KAOS
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        val indonesia = Locale("in","ID")
        val formattedPrice = NumberFormat.getCurrencyInstance(indonesia).format(calculatedPrice)
        return formattedPrice
    }
    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}