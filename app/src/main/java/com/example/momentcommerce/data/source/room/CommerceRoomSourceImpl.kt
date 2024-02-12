package com.example.momentcommerce.data.source.room

import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.model.LikedProduct
import com.example.momentcommerce.util.MathUtils.roundDecimal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CommerceRoomSourceImpl(
    private val commerceDao: CommerceDao
) : CommerceRoomSource {
    override suspend fun insertProduct(productBag: BagProduct) {
        commerceDao.insertProduct(productBag)
    }

    override suspend fun insertLikedProduct(likedProduct: LikedProduct) {
        commerceDao.insertLikedProduct(likedProduct)
    }

    override suspend fun deleteLikedProduct(productID: Int) {
        commerceDao.deleteLikedProduct(productID)
    }


    override suspend fun updateProduct(
        productID: Int,
        newProductCount: Int,
        newProductAmount: Double
    ) {
        commerceDao.updateProduct(productID, newProductCount, newProductAmount)
    }

    override suspend fun deleteProduct(productID: Int) {
        commerceDao.deleteProduct(productID)
    }

    override suspend fun getProductsFromBag(): List<BagProduct> {
        return commerceDao.getProductsFromBag()
    }

    override  fun getProductQuantity(): Flow<Int> {
        return commerceDao.getProductQuantity()
    }

    override fun getProductAmount(): Flow<Double?> {
        return commerceDao.getTotalAmountFlow()
            .map { originalValue ->
                roundDecimal(originalValue ?: 0.0, 2) // null kontrolü ve yuvarlama işlemi
            }
    }

    override suspend fun clearBasketProductTable() {
        commerceDao.clearBasketProductTable()
    }

    override suspend fun clearLikedProductsTable() {
        commerceDao.clearLikedProductsTable()
    }

    override suspend fun getProductFromBag(productID: Int): BagProduct {
        return commerceDao.getProductFromBag(productID)
    }

    override suspend fun getLikedProduct(productID: Int): LikedProduct {
        return commerceDao.getLikedProduct(productID)
    }
}