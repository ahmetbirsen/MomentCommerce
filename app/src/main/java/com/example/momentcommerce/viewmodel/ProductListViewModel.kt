package com.example.momentcommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentcommerce.data.repo.ProductsRepository
import com.example.momentcommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductsRepository
): ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList : LiveData<List<Product>>
        get() = _productList

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _productList.postValue(repository.getProducts())
        }
    }
}