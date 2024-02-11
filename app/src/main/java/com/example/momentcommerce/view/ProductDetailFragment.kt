package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val args by navArgs<ProductDetailFragmentArgs>()
    private var fragmentBinding : FragmentProductDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductDetailBinding.bind(view)
        fragmentBinding = binding

        fragmentBinding?.productNameTitle?.text = args.currentProduct.name
        val resName = args.currentProduct.imageName ?: ""
        val resId = context?.resources?.getIdentifier(resName, "drawable", context?.packageName)
        if (resId != 0) {
            fragmentBinding?.productImage?.setImageResource(resId ?: 0)
        } else {
            fragmentBinding?.productImage?.setImageResource(R.drawable.ic_launcher_foreground)
        }
        fragmentBinding?.productName?.text = args.currentProduct.name
        fragmentBinding?.productCategory?.text = args.currentProduct.category
        fragmentBinding?.productColor?.text = args.currentProduct.color
        fragmentBinding?.productPrice?.text = args.currentProduct.price.toString()
        fragmentBinding?.productTotalPrice?.text = args.currentProduct.price.toString()

    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}