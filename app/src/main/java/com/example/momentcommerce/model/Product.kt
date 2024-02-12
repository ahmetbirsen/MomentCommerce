package com.example.momentcommerce.model

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.*

@Entity(tableName = "PRODUCTS")
@Parcelize
@Serializable
data class Product(
	@SerializedName("image_name") var imageName: String? = null,
	val color: String? = null,
	val price: Double? = null,
	val name: String? = null,
	val currency: String? = null,
	@PrimaryKey(autoGenerate = false)
	val id: Int? = null,
	val category: String? = null
) : Parcelable
