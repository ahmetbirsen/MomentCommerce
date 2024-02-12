package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momentcommerce.R
import com.example.momentcommerce.adapter.ProductBagAdapter
import com.example.momentcommerce.databinding.FragmentShoppingBagBinding
import com.example.momentcommerce.viewmodel.ProductListViewModel
import com.example.momentcommerce.viewmodel.ShoppingBagViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingBagFragment @Inject constructor(
    private val productBagAdapter: ProductBagAdapter
    ) : Fragment(R.layout.fragment_shopping_bag) {

    private var fragmentBinding : FragmentShoppingBagBinding? = null
    private val shoppingBagViewModel : ShoppingBagViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShoppingBagBinding.bind(view)
        fragmentBinding = binding

        observeProductListFromBag()

        fragmentBinding?.confirmBasket?.setOnClickListener {
            findNavController().navigate(ShoppingBagFragmentDirections.actionShoppingBagFragmentToOrderConfirmationFragment())
        }

        productBagAdapter.setOnItemUpdateListener {
            shoppingBagViewModel.updateProductFromBag(it.id!!, it.totalCount!!, it.totalAmount!!)
        }

        productBagAdapter.setOnItemDeleteFromBagListener {
            shoppingBagViewModel.deleteProductFromBag(it)
            shoppingBagViewModel.getProductsFromBag()
        }

        observeTotalAmount()
        observeProductCount()

    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

    private fun observeProductListFromBag() {

        shoppingBagViewModel.productListBag.observe(viewLifecycleOwner, Observer {

            fragmentBinding?.productBagRV?.apply {
                adapter = productBagAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false )
            }
            productBagAdapter.products = it.toMutableList()
        })
    }

    private fun observeTotalAmount(){
        shoppingBagViewModel.totalAmount.observe(viewLifecycleOwner, Observer {
            fragmentBinding?.lastTotalProductAmount?.text = it.toString()
        })
    }

    private fun observeProductCount(){
        shoppingBagViewModel.totalCount.observe(viewLifecycleOwner, Observer {
            fragmentBinding?.totalCount?.text = it.toString()
        })
    }

}