package com.example.momentcommerce.data.repo

import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.model.LikedProduct
import com.example.momentcommerce.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts() : List<Product>

    suspend fun insertProduct(productBag : BagProduct)

    suspend fun insertLikedProduct(likedProduct: LikedProduct)

    suspend fun deleteLikedProduct(productID: Int)

    suspend fun updateProduct(productID : Int, newProductCount : Int,newProductAmount : Double)

    suspend fun deleteProduct(productID: Int)

    suspend fun getProductsFromBag() : List<BagProduct>

    fun getProductCount() : Flow<Int>

    fun getProductAmountBag() : Flow<Double?>

    suspend fun clearBasketProductTable()

    suspend fun clearLikedProductsTable()
}