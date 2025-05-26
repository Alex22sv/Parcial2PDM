package com.ae22mp.parcial2pdm.model

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    var addedToCart: Boolean,
)