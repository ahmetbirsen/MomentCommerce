package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momentcommerce.R
import com.example.momentcommerce.view.adapter.ProductBagAdapter
import com.example.momentcommerce.view.adapter.ProductListAdapter
import com.example.momentcommerce.databinding.FragmentShoppingBagBinding
import com.example.momentcommerce.viewmodel.ProductListViewModel
import com.example.momentcommerce.viewmodel.ShoppingBagViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingBagFragment
//@Inject constructor(
//    private val productBagAdapter: ProductBagAdapter
//    )
    : Fragment(R.layout.fragment_shopping_bag) {

    private var fragmentBinding : FragmentShoppingBagBinding? = null
    private val shoppingBagViewModel : ShoppingBagViewModel by viewModels()
    private val productBagAdapter: ProductBagAdapter by lazy { ProductBagAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShoppingBagBinding.bind(view)
        fragmentBinding = binding

        observeProductListFromBag()

        fragmentBinding?.confirmBasket?.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder
                .setMessage("Are you sure you want to order?")
                .setTitle("Order Confirmation")
                .setPositiveButton("Yes") { dialog, which ->
                    findNavController().navigate(ShoppingBagFragmentDirections.actionShoppingBagFragmentToOrderConfirmationFragment())
                }
                .setNegativeButton("No") { dialog, which ->
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
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
        shoppingBagViewModel.productListBag.observe(viewLifecycleOwner) {
                fragmentBinding?.productBagRV?.apply {
                    adapter = productBagAdapter
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false )
                }
                productBagAdapter.products = it.toMutableList()
        }
    }

    private fun observeTotalAmount(){
        shoppingBagViewModel.totalAmount.observe(viewLifecycleOwner, Observer {
            if (it == 0.0){
                fragmentBinding?.emptyBagMsg?.visibility = View.VISIBLE
                fragmentBinding?.cardArea?.visibility = View.GONE
                fragmentBinding?.confirmBasket?.visibility = View.GONE
                fragmentBinding?.mainArea?.visibility = View.GONE
            }
            fragmentBinding?.lastTotalProductAmount?.text = it.toString()
        })
    }

    private fun observeProductCount(){
        shoppingBagViewModel.totalCount.observe(viewLifecycleOwner, Observer {
            fragmentBinding?.totalCount?.text = it.toString()
        })
    }

}