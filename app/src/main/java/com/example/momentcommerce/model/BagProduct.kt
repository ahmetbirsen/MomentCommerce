package com.example.momentcommerce.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "BAG_PRODUCTS")
@Serializable
data class BagProduct(
    @SerializedName("image_name") var imageName: String? = null,
    val color: String? = null,
    val price: Double? = null,
    val name: String? = null,
    val currency: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val category: String? = null,
    val totalAmount : Double? = null,
    val totalCount : Int? = null
)
