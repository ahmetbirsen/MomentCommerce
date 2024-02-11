package com.example.momentcommerce.data.repo

import com.example.momentcommerce.model.Product

interface ProductsRepository {

    suspend fun getProducts() : List<Product>
}