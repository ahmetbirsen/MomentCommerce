package com.example.momentcommerce.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "LIKED_PRODUCTS")
data class LikedProduct(
    @PrimaryKey(autoGenerate = true)
    val id :Long? = null,
    val productID : Int ?= null
)
