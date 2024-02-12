package com.example.momentcommerce.data.source.room

import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.model.LikedProduct
import kotlinx.coroutines.flow.Flow

interface CommerceRoomSource {

    suspend fun insertProduct(productBag : BagProduct)

    suspend fun insertLikedProduct(likedProduct: LikedProduct)

    suspend fun deleteLikedProduct(productID: Int)

    suspend fun updateProduct(productID : Int, newProductCount : Int,newProductAmount : Double)

    suspend fun deleteProduct(productID: Int)

    suspend fun getProductsFromBag() : List<BagProduct>

    fun getProductQuantity() : Flow<Int>

    fun getProductAmount() : Flow<Double?>

    suspend fun clearBasketProductTable()

    suspend fun clearLikedProductsTable()
}