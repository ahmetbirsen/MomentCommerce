package com.example.momentcommerce.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.model.LikedProduct
import com.example.momentcommerce.model.Product

@Database(
    entities = [Product::class, BagProduct::class, LikedProduct::class],
    version = 1,
    exportSchema = false
)
abstract class CommerceDatabase : RoomDatabase() {

    abstract fun commerceDao() : CommerceDao
}