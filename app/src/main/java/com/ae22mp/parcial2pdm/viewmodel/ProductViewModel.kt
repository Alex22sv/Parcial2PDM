package com.ae22mp.parcial2pdm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ae22mp.parcial2pdm.local.localProducts
import com.ae22mp.parcial2pdm.model.Product
import kotlin.Int

class ProductViewModel(): ViewModel() {

    private val _products = MutableLiveData<List<Product>?>()
    private val _filteredProducts = MutableLiveData<List<Product>?>()
    private val _product = MutableLiveData<Product?>()
    val products: LiveData<List<Product>?> = _products
    val filteredProducts: LiveData<List<Product>?> = _filteredProducts
    val product: LiveData<Product?> = _product

    fun loadProducts() {
        _products.postValue(localProducts)
    }
    fun getProductById(id: String) {
        val product = _products.value?.find { it.id == id.toInt() }
        _product.postValue(product)
    }
    fun addProductToCart(product: Product) {
        product.addedToCart = true
    }
    fun removeToCart(product: Product) {
        product.addedToCart = false
    }
    fun searchProducts(query: String) {
        val filter = _products.value?.mapNotNull { product ->
            val nameMatch = product.name.contains(query, ignoreCase = true)
            val categoryMatch = product.category.contains(query, ignoreCase = true)
            val score = when {
                nameMatch -> 2
                categoryMatch -> 1
                else -> 0
            }
            if(score>0) product to score else null
        }?.sortedByDescending { it.second }?.map { it.first }
        _filteredProducts.postValue(filter)
    }
}