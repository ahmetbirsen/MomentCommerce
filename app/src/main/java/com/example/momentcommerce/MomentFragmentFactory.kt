package com.example.momentcommerce

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.momentcommerce.adapter.ProductBagAdapter
import com.example.momentcommerce.adapter.ProductListAdapter
import com.example.momentcommerce.view.ProductListFragment
import com.example.momentcommerce.view.ShoppingBagFragment
import javax.inject.Inject

class MomentFragmentFactory @Inject constructor(
    private val productRecyclerViewAdapter : ProductListAdapter,
    private val productBagAdapter: ProductBagAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return  when(className){
            ProductListFragment::class.java.name -> ProductListFragment(productRecyclerViewAdapter)
            ShoppingBagFragment::class.java.name -> ShoppingBagFragment(productBagAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}