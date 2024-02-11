package com.example.momentcommerce.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.momentcommerce.adapter.ProductListAdapter
import com.example.momentcommerce.view.ProductListFragment
import javax.inject.Inject

class MomentFragmentFactory @Inject constructor(
    private val productRecyclerViewAdapter : ProductListAdapter,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return  when(className){
            ProductListFragment::class.java.name -> ProductListFragment(productRecyclerViewAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}