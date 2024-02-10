package com.example.momentcommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.momentcommerce.databinding.FragmentOrderConfirmationBinding
import com.example.momentcommerce.databinding.FragmentProductListBinding

class OrderConfirmationFragment : Fragment(R.layout.fragment_order_confirmation) {

    private var fragmentBinding : FragmentOrderConfirmationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentOrderConfirmationBinding.bind(view)
        fragmentBinding = binding
    }

}