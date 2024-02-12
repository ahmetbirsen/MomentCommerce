package com.example.momentcommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.ItemBagProductBinding
import com.example.momentcommerce.databinding.ItemProductBinding
import com.example.momentcommerce.model.BagProduct
import com.example.momentcommerce.model.Product
import com.example.momentcommerce.util.MathUtils.roundDecimal
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.roundToInt

class ProductBagAdapter @Inject constructor() :
    RecyclerView.Adapter<ProductBagAdapter.ProductViewHolder>() {
    class ProductViewHolder(val binding: ItemBagProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var onItemUpdateBagListener: ((BagProduct) -> Unit)? = null
    private var onItemDeleteFromBagListener: ((Int) -> Unit)? = null

    fun setOnItemDeleteFromBagListener(listener: (Int) -> Unit) {
        onItemDeleteFromBagListener = listener
    }

    fun setOnItemUpdateListener(listener: (BagProduct) -> Unit) {
        onItemUpdateBagListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val ui = ItemBagProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(ui)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = products[position]
        val itemView = holder.binding

        holder.itemView.apply {
            itemView.productName.text = currentItem.name
            itemView.totalAmount.text = (currentItem.totalAmount ?: 0.0).toString()
            itemView.productCount.text = currentItem.totalCount.toString()
            val resName = currentItem.imageName ?: ""
            val resId = context.resources.getIdentifier(resName, "drawable", context.packageName)
            if (resId != 0) {
                itemView.productImage.setImageResource(resId)
            } else {
                itemView.productImage.setImageResource(R.drawable.ic_launcher_foreground)
            }
        }

        itemView.increaseCount.setOnClickListener {
            var count = itemView.productCount.text.toString().toInt()
            var totalPrice = itemView.totalAmount.text.toString().toDouble()

            count++
            totalPrice = roundDecimal((totalPrice + (currentItem.price ?: 0.0)), 3)

            itemView.productCount.text = count.toString()
            itemView.totalAmount.text = totalPrice.toString()

            onItemUpdateBagListener?.let {
                it(
                    BagProduct(
                        currentItem.imageName,
                        currentItem.color,
                        currentItem.price,
                        currentItem.name,
                        currentItem.currency,
                        currentItem.id,
                        currentItem.category,
                        totalPrice,
                        count.toInt()
                    )
                )
            }
        }

        itemView.decreaseCount.setOnClickListener {
            var count = itemView.productCount.text.toString().toInt()
            var totalPrice = itemView.totalAmount.text.toString().toDouble()
            count--
            totalPrice = roundDecimal((totalPrice - (currentItem.price ?: 0.0)), 2)
            if (count <= 0.0) {
                onItemDeleteFromBagListener?.let {
                    it(currentItem.id!!)
                }
            } else {
                itemView.productCount.text = count.toString()
                itemView.totalAmount.text = totalPrice.toString()
                onItemUpdateBagListener?.let {
                    it(
                        BagProduct(
                            currentItem.imageName,
                            currentItem.color,
                            currentItem.price,
                            currentItem.name,
                            currentItem.currency,
                            currentItem.id,
                            currentItem.category,
                            totalPrice,
                            count
                        )
                    )
                }
            }
        }

        itemView.deleteProduct.setOnClickListener {
            onItemDeleteFromBagListener?.let {
                it(currentItem.id!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    private val diffUtil = object : DiffUtil.ItemCallback<BagProduct>() {
        override fun areItemsTheSame(oldItem: BagProduct, newItem: BagProduct): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BagProduct, newItem: BagProduct): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)
    var products: MutableList<BagProduct>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


}