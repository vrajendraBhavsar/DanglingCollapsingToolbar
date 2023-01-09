package com.example.model

import androidx.annotation.DrawableRes

data class Item(
    val itemName: String? = null,
    val itemDescription: String? = null,
    @DrawableRes val itemImage: Int? = null,
)