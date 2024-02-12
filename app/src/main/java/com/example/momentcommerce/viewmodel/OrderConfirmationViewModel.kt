package com.example.momentcommerce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentcommerce.data.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderConfirmationViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {




    fun clearBag() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearBasketProductTable()
    }

    fun clearLikedProducts() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearLikedProductsTable()
    }

}