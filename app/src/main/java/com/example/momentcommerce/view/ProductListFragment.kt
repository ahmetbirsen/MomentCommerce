package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.momentcommerce.R
import com.example.momentcommerce.adapter.ProductListAdapter
import com.example.momentcommerce.databinding.FragmentProductListBinding
import com.example.momentcommerce.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProductListFragment @Inject constructor(
    private val productListAdapter: ProductListAdapter
) : Fragment(R.layout.fragment_product_list) {

    private var fragmentBinding : FragmentProductListBinding? = null
    private lateinit var productListViewModel: ProductListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListViewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)

        val binding = FragmentProductListBinding.bind(view)

        observeProductList()
        fragmentBinding = binding
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }

    private fun observeProductList() {

        productListViewModel.productList.observe(viewLifecycleOwner, Observer {

            fragmentBinding?.productsRV?.apply {
                adapter = productListAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            productListAdapter.products = it.toMutableList()
        })
    }

}