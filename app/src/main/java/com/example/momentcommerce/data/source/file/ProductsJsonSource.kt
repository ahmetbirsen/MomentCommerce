package com.example.momentcommerce.data.source.file

import com.example.momentcommerce.data.model.Product

interface ProductsJsonSource {

    suspend fun getProducts() : List<Product>
}