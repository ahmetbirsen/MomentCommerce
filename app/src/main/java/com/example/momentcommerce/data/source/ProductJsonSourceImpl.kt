package com.example.momentcommerce.data.source

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.momentcommerce.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStreamReader

class ProductJsonSourceImpl(
    private val context: Context
) : ProductsJsonSource {
    override suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        try {
            context.assets.open("Products.json").use { inputStream ->
                val reader = InputStreamReader(inputStream)
                val productListType = object : TypeToken<List<Product>>() {}.type
                val productsList: List<Product> = Gson().fromJson(reader, productListType)
                for (product in productsList){
                    println(product.imageName)
                    product.imageName = product.imageName?.replace("-", "_")
                }
                productsList
            }
        } catch (e: Exception) {
            println("Hata : ${e}")
            emptyList()
        }
    }
}