package com.example.momentcommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.momentcommerce.data.repo.ProductsRepository
import com.example.momentcommerce.data.model.BagProduct
import com.example.momentcommerce.data.model.LikedProduct
import com.example.momentcommerce.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    val totalAmount: LiveData<Double?> = repository.getProductAmountBag().asLiveData()

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        _productList.postValue(repository.getProducts())
    }

    fun sortProducts(orderType: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val sortedProductList = when (orderType) {
                "price" -> _productList.value?.sortedByDescending { it.price }
                else -> _productList.value
            }
            _productList.postValue(sortedProductList ?: repository.getProducts())
        }
    }


    fun insertProductToBag(productBag : BagProduct) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertProduct(productBag)
    }

    fun updateProductFromBag(productID: Int, newProductCount : Int,newProductAmount : Double) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateProduct(productID, newProductCount, newProductAmount)
    }

    fun deleteProductFromBag(productID: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteProduct(productID)
    }

    fun insertLikedProduct(likedProduct: LikedProduct) = viewModelScope.launch(Dispatchers.IO){
        repository.insertLikedProduct(likedProduct)
    }

    fun deleteLikedProduct(productID: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteLikedProduct(productID)
    }

    fun clearBag() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearBasketProductTable()
    }



}