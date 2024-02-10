package com.example.momentcommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.momentcommerce.databinding.FragmentProductDetailBinding
import com.example.momentcommerce.databinding.FragmentProductListBinding


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