package com.example.momentcommerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentProductDetailBinding
import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.util.MathUtils
import com.example.momentcommerce.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val args by navArgs<ProductDetailFragmentArgs>()
    private var fragmentBinding: FragmentProductDetailBinding? = null
    private val productDetailViewModel: ProductDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductDetailBinding.bind(view)
        fragmentBinding = binding

        productDetailViewModel.getProductFromBag(args.currentProduct.id ?: 0)
        productDetailViewModel.getLikedProduct(args.currentProduct.id ?: 0)

        observeIsAdded()

        observeIsLiked()

        oberverBagProduct()



        fragmentBinding?.productNameTitle?.text = args.currentProduct.name

        val resId = context?.resources?.getIdentifier(
            args.currentProduct.imageName ?: "",
            "drawable",
            context?.packageName
        )
        if (resId != 0) {
            fragmentBinding?.productImage?.setImageResource(resId ?: 0)
        } else {
            fragmentBinding?.productImage?.setImageResource(R.drawable.ic_launcher_foreground)
        }

        fragmentBinding?.productName?.text = args.currentProduct.name
        fragmentBinding?.productCategory?.text = args.currentProduct.category
        fragmentBinding?.productColor?.text = args.currentProduct.color
        fragmentBinding?.productPrice?.text = args.currentProduct.price.toString()
        fragmentBinding?.productCurrency?.text = args.currentProduct.currency.toString()


    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }

    private fun oberverBagProduct() {
        productDetailViewModel.productFromBag.observe(viewLifecycleOwner, Observer {
            it?.let {
                fragmentBinding?.bagOperator?.visibility = View.VISIBLE
                fragmentBinding?.removeFromBag?.visibility = View.VISIBLE
                fragmentBinding?.addToBagCard?.visibility = View.GONE
                fragmentBinding?.productCount?.text = it.totalCount.toString()
                fragmentBinding?.productTotalAmount?.text = it.totalAmount.toString()
            } ?: kotlin.run {
                fragmentBinding?.addToBagCard?.visibility = View.VISIBLE
            }

        })
    }

    private fun observeIsAdded() {
        productDetailViewModel.isAddedToBag.observe(viewLifecycleOwner) {

            fragmentBinding?.addToBagCard?.setOnClickListener {
                fragmentBinding?.addToBagCard?.visibility = View.GONE
                fragmentBinding?.bagOperator?.visibility = View.VISIBLE
                fragmentBinding?.removeFromBag?.visibility = View.VISIBLE
                fragmentBinding?.productCount?.text = "1"
                fragmentBinding?.productTotalAmount?.text = args.currentProduct.price!!.toString()
                productDetailViewModel.insertProductToBag(
                    BagProduct(
                        args.currentProduct.imageName,
                        args.currentProduct.color,
                        args.currentProduct.price,
                        args.currentProduct.name,
                        args.currentProduct.currency,
                        args.currentProduct.id,
                        args.currentProduct.category,
                        args.currentProduct.price,
                        1
                    )
                )
//                onItemBagListener?.let {
//                    it(
//                        BagProduct(
//                            currentItem.imageName,
//                            currentItem.color,
//                            currentItem.price,
//                            currentItem.name,
//                            currentItem.currency,
//                            currentItem.id,
//                            currentItem.category,
//                            currentItem.price,
//                            1
//                        )
//                    )
//                }
            }

            fragmentBinding?.increaseCount?.setOnClickListener {
                var count = fragmentBinding?.productCount?.text.toString().toInt()
                var totalPrice = fragmentBinding?.productTotalAmount?.text.toString().toDouble()
                count++
                totalPrice = MathUtils.roundDecimal((totalPrice + (args.currentProduct.price!!)), 3)

                fragmentBinding?.productCount?.text = count.toString()
                fragmentBinding?.productTotalAmount?.text = totalPrice.toString()

                productDetailViewModel.updateProductFromBag(args.currentProduct.id!!, count, totalPrice)

            }

            fragmentBinding?.decreaseCount?.setOnClickListener {
                var count = fragmentBinding?.productCount?.text.toString().toInt()
                var totalPrice = fragmentBinding?.productTotalAmount?.text.toString().toDouble()
                count--
                totalPrice = MathUtils.roundDecimal((totalPrice - (args.currentProduct.price!!)), 2)
                if (count <= 0.0) {
                    fragmentBinding?.addToBagCard?.visibility = View.VISIBLE
                    fragmentBinding?.bagOperator?.visibility = View.GONE
                    fragmentBinding?.removeFromBag?.visibility = View.GONE
                    productDetailViewModel.deleteProductFromBag(args.currentProduct.id!!)
                } else {
                    fragmentBinding?.productCount?.text = count.toString()
                    fragmentBinding?.productTotalAmount?.text = totalPrice.toString()
                    productDetailViewModel.updateProductFromBag(args.currentProduct.id!!, count, totalPrice)
                }
            }

            fragmentBinding?.removeFromBag?.setOnClickListener {
                fragmentBinding?.addToBagCard?.visibility = View.VISIBLE
                fragmentBinding?.bagOperator?.visibility = View.GONE
                fragmentBinding?.removeFromBag?.visibility = View.GONE
                productDetailViewModel.deleteProductFromBag(args.currentProduct.id!!)
            }
        }
    }

    private fun observeIsLiked(){
        productDetailViewModel.isLiked.observe(viewLifecycleOwner){
            if(it){
                fragmentBinding?.likeBtn?.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            }
        }
    }
}