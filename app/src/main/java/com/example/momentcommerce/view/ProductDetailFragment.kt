package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var fragmentBinding : FragmentProductDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductDetailBinding.bind(view)
        fragmentBinding = binding
    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}