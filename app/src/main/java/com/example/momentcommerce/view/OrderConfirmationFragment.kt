package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentOrderConfirmationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderConfirmationFragment : Fragment(R.layout.fragment_order_confirmation) {

    private var fragmentBinding : FragmentOrderConfirmationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentOrderConfirmationBinding.bind(view)
        fragmentBinding = binding
    }

}