package com.example.momentcommerce.data.source.file

import android.content.Context
import com.example.momentcommerce.data.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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