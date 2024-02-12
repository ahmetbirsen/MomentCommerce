package com.example.momentcommerce.data.source.file

import com.example.momentcommerce.model.Product

interface ProductsJsonSource {

    suspend fun getProducts() : List<Product>
}