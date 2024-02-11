package com.example.momentcommerce.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*
@Serializable
data class Product(
	val imageName: String? = null,
	val color: String? = null,
	val price: Double? = null,
	val name: String? = null,
	val currency: String? = null,
	val id: Int? = null,
	val category: String? = null
)
