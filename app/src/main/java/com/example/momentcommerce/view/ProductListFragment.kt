package com.example.momentcommerce.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.momentcommerce.R
import com.example.momentcommerce.adapter.ProductListAdapter
import com.example.momentcommerce.databinding.FragmentProductListBinding
import com.example.momentcommerce.model.LikedProduct
import com.example.momentcommerce.viewmodel.ProductListViewModel
import com.example.momentcommerce.viewmodel.ShoppingBagViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private var fragmentBinding: FragmentProductListBinding? = null
    private val productListViewModel: ProductListViewModel by viewModels()
//    private val shoppingBagViewModel :ShoppingBagViewModel by viewModels( )
    private val productListAdapter: ProductListAdapter by lazy { ProductListAdapter() }

    private lateinit var orderList: Array<String>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProductListBinding.bind(view)
        fragmentBinding = binding

        orderList = arrayOf("Price")

        observeProductList()

        fragmentBinding?.sortProduct?.setOnClickListener {
            sortDialog()
        }


        binding.goBag.setOnClickListener {
            findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToShoppingBagFragment())
        }


        observeTotalAmount()

        productListAdapter.setOnItemClickListener { product ->
            findNavController().navigate(
                ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                    product
                )
            )
        }

        productListAdapter.setOnItemBagListener {
            productListViewModel.insertProductToBag(it)
        }

        productListAdapter.setOnItemUpdateListener {
            productListViewModel.updateProductFromBag(it.id!!, it.totalCount!!, it.totalAmount!!)
        }

        productListAdapter.setOnItemDeleteFromBagListener {
            productListViewModel.deleteProductFromBag(it.id!!)
        }

        productListAdapter.setOnItemLikedListener {
            productListViewModel.insertLikedProduct(LikedProduct(productID = it.id))
        }

        productListAdapter.setOnItemDeleteLikedListener {
            productListViewModel.deleteLikedProduct(it)
        }

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

    private fun sortDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Sort By")

        alertDialogBuilder.setItems(orderList) { dialog: DialogInterface, which: Int ->
            val selectedOrder = orderList[which].lowercase()
            productListViewModel.sortProducts(selectedOrder)
            fragmentBinding?.productsRV?.smoothScrollToPosition(0)
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun observeTotalAmount() {
        productListViewModel.totalAmount.observe(viewLifecycleOwner, Observer {
            fragmentBinding?.totalAmount?.text = it.toString()
        })
    }



}