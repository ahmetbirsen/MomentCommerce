package com.example.momentcommerce.model

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.*

@Parcelize
@Serializable
data class Product(
	@SerializedName("image_name") var imageName: String? = null,
	val color: String? = null,
	val price: Double? = null,
	val name: String? = null,
	val currency: String? = null,
	val id: Int? = null,
	val category: String? = null
) : Parcelable
