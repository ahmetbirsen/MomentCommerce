package com.example.momentcommerce.data.source.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.momentcommerce.data.model.BagProduct
import com.example.momentcommerce.data.model.LikedProduct
import kotlinx.coroutines.flow.Flow


@Dao
interface CommerceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productBagProduct: BagProduct)

    @Insert
    suspend fun insertLikedProduct(likedProduct: LikedProduct)

    @Query("DELETE FROM LIKED_PRODUCTS WHERE productID = :productID")
    suspend fun deleteLikedProduct(productID: Int)

    @Query("UPDATE BAG_PRODUCTS SET totalCount = :newAmount, totalAmount = :newPrice WHERE id = :productId")
    suspend fun updateProduct(productId: Int, newAmount: Int, newPrice: Double)

    @Query("DELETE FROM BAG_PRODUCTS WHERE id = :productID")
    suspend fun deleteProduct(productID: Int)

    @Query("SELECT * FROM BAG_PRODUCTS")
    suspend fun getProductsFromBag() : List<BagProduct>

    @Query("SELECT SUM(totalCount) FROM BAG_PRODUCTS")
    fun getProductQuantity() : Flow<Int>

    @Query("DELETE FROM BAG_PRODUCTS")
    suspend fun clearBasketProductTable()

    @Query("DELETE FROM LIKED_PRODUCTS")
    suspend fun clearLikedProductsTable()

    @Query("SELECT SUM(totalAmount) FROM BAG_PRODUCTS")
    fun getTotalAmountFlow(): Flow<Double?>

    @Query("SELECT * FROM BAG_PRODUCTS WHERE id = :productID")
    suspend fun getProductFromBag(productID: Int) : BagProduct

    @Query("SELECT * FROM LIKED_PRODUCTS WHERE productID = :productID")
    suspend fun getLikedProduct(productID: Int) : LikedProduct

}

