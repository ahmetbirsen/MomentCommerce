package com.example.momentcommerce.data.repo

import com.example.momentcommerce.data.source.ProductsJsonSource
import com.example.momentcommerce.model.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productJsonSource : ProductsJsonSource
): ProductsRepository {
    override suspend fun getProducts(): List<Product> {
        return productJsonSource.getProducts()
    }
}