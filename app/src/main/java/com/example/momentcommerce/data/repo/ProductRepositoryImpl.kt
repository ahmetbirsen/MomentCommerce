package com.example.momentcommerce.data.repo

import com.example.momentcommerce.data.source.file.ProductsJsonSource
import com.example.momentcommerce.data.source.room.CommerceDao
import com.example.momentcommerce.data.source.room.CommerceRoomSource
import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.model.LikedProduct
import com.example.momentcommerce.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productJsonSource : ProductsJsonSource,
    private val productRoomSource: CommerceRoomSource
): ProductsRepository {
    override suspend fun getProducts(): List<Product> {
        return productJsonSource.getProducts()
    }

    override suspend fun insertProduct(productBag: BagProduct) {
        productRoomSource.insertProduct(productBag)
    }

    override suspend fun insertLikedProduct(likedProduct: LikedProduct) {
        productRoomSource.insertLikedProduct(likedProduct)
    }

    override suspend fun deleteLikedProduct(productID: Int) {
        productRoomSource.deleteLikedProduct(productID)
    }

    override suspend fun updateProduct(
        productID: Int,
        newProductCount: Int,
        newProductAmount: Double
    ) {
        productRoomSource.updateProduct(productID, newProductCount, newProductAmount)
    }

    override suspend fun deleteProduct(productID: Int) {
        productRoomSource.deleteProduct(productID)
    }

    override suspend fun getProductsFromBag(): List<BagProduct> {
       return productRoomSource.getProductsFromBag()
    }

    override fun getProductCount(): Flow<Int> {
        return productRoomSource.getProductQuantity()
    }


    override fun getProductAmountBag(): Flow<Double?> {
        return productRoomSource.getProductAmount()
    }

    override suspend fun clearBasketProductTable() {
        productRoomSource.clearBasketProductTable()
    }

    override suspend fun clearLikedProductsTable() {
        productRoomSource.clearLikedProductsTable()
    }

    override suspend fun getProductFromBag(productID: Int): BagProduct {
        return productRoomSource.getProductFromBag(productID)
    }

    override suspend fun getLikedProduct(productID: Int): LikedProduct {
        return productRoomSource.getLikedProduct(productID)
    }


}