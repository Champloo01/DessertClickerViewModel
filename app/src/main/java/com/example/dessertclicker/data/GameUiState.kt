package com.example.dessertclicker.data

import androidx.annotation.DrawableRes

data class GameUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = Datasource.dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = Datasource.dessertList[currentDessertIndex].imageId
)
