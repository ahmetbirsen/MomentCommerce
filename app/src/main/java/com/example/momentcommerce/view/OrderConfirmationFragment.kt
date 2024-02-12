package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentOrderConfirmationBinding
import com.example.momentcommerce.viewmodel.OrderConfirmationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderConfirmationFragment : Fragment(R.layout.fragment_order_confirmation) {

    private var fragmentBinding : FragmentOrderConfirmationBinding? = null
    private val orderConfirmationViewModel : OrderConfirmationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentOrderConfirmationBinding.bind(view)
        fragmentBinding = binding

        observeTotalAmount()
        observeProductCount()

        fragmentBinding?.closeBtn?.setOnClickListener {
            orderConfirmationViewModel.clearBag()
            orderConfirmationViewModel.clearLikedProducts()
            findNavController().navigate(OrderConfirmationFragmentDirections.actionOrderConfirmationFragmentToProductListFragment())
        }
    }

    private fun observeTotalAmount(){
        orderConfirmationViewModel.totalAmount.observe(viewLifecycleOwner) {
            fragmentBinding?.lastTotalProduct?.text = it.toString()
        }
    }

    private fun observeProductCount(){
        orderConfirmationViewModel.totalCount.observe(viewLifecycleOwner){
            fragmentBinding?.totalCount?.text = it.toString()
        }
    }

}