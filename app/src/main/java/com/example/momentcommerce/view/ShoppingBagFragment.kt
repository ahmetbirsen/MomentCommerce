package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentShoppingBagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingBagFragment : Fragment(R.layout.fragment_shopping_bag) {
    private var fragmentBinding : FragmentShoppingBagBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShoppingBagBinding.bind(view)
        fragmentBinding = binding
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}