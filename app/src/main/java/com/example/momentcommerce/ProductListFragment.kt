package com.example.momentcommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.momentcommerce.databinding.FragmentProductListBinding


class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private var fragmentBinding : FragmentProductListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductListBinding.bind(view)
        fragmentBinding = binding
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }

}