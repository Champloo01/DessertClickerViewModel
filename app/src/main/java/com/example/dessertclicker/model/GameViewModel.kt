package com.example.dessertclicker.model

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.data.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun updateUiState() {
        val desertsSold = uiState.value.dessertsSold.plus(1)
        val nextIndex = determineDessertIndex(dessertsSold = desertsSold)
        _uiState.update { currentState ->
            currentState.copy(
                dessertsSold = desertsSold,
                revenue = uiState.value.revenue + uiState.value.currentDessertPrice,
                currentDessertIndex = nextIndex,
                currentDessertPrice = dessertList[nextIndex].price,
                currentDessertImageId = dessertList[nextIndex].imageId
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in Datasource.dessertList.indices) {
            if (dessertsSold >= Datasource.dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                break
            }
        }
        return dessertIndex
    }
}