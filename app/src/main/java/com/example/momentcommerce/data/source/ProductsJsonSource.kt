package com.example.momentcommerce.data.source

import com.example.momentcommerce.model.Product

interface ProductsJsonSource {

    suspend fun getProducts() : List<Product>
}