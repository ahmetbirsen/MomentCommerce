package com.example.momentcommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.momentcommerce.data.repo.ProductsRepository
import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingBagViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _productListBag = MutableLiveData<List<BagProduct>>()
    val productListBag: LiveData<List<BagProduct>>
        get() = _productListBag


    val totalAmount: LiveData<Double?> = repository.getProductAmountBag().asLiveData()
    val totalCount : LiveData<Int> = repository.getProductCount().asLiveData()



    init {
        getProductsFromBag()
    }

    fun getProductsFromBag() = viewModelScope.launch {
        _productListBag.postValue(repository.getProductsFromBag())
    }

    fun updateProductFromBag(productID: Int, newProductCount : Int,newProductAmount : Double) = viewModelScope.launch(
        Dispatchers.IO) {
        repository.updateProduct(productID, newProductCount, newProductAmount)
    }

    fun deleteProductFromBag(productID: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteProduct(productID)
    }

    fun clearBag() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearBasketProductTable()
    }

}