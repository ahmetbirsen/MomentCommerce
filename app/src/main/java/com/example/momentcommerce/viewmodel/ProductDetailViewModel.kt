package com.example.momentcommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
class ProductDetailViewModel  @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _isAddedToBag = MutableLiveData<Boolean>(false)
    val isAddedToBag: LiveData<Boolean>
        get() = _isAddedToBag

    private val _isLiked = MutableLiveData<Boolean>(false)
    val isLiked: LiveData<Boolean>
        get() = _isLiked


    private val _productFromBag = MutableLiveData<BagProduct>()
    val productFromBag: LiveData<BagProduct>
        get() = _productFromBag

    fun getProductFromBag(productID : Int) = viewModelScope.launch(Dispatchers.IO) {
            _productFromBag.postValue(repository.getProductFromBag(productID))
            _isAddedToBag.postValue(true)
    }

    fun insertProductToBag(productBag : BagProduct) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertProduct(productBag)
    }

    fun insertLikedProduct(likedProduct: LikedProduct) = viewModelScope.launch(Dispatchers.IO){
        repository.insertLikedProduct(likedProduct)
    }

    fun updateProductFromBag(productID: Int, newProductCount : Int,newProductAmount : Double) = viewModelScope.launch(
        Dispatchers.IO) {
        repository.updateProduct(productID, newProductCount, newProductAmount)
    }

    fun deleteProductFromBag(productID: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteProduct(productID)
    }

    fun deleteLikedProduct(productID: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteLikedProduct(productID)
    }

    fun getLikedProduct(productID : Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getLikedProduct(productID)?.let {
            _isLiked.postValue(true)
        }

    }

}