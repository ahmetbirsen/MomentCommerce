package com.example.momentcommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.momentcommerce.databinding.FragmentProductListBinding
import com.example.momentcommerce.databinding.FragmentShoppingBagBinding

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