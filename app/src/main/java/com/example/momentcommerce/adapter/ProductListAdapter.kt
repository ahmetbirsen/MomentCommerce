package com.example.momentcommerce.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentOrderConfirmationBinding
import com.example.momentcommerce.databinding.ItemProductBinding
import com.example.momentcommerce.model.Product
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.roundToInt

class ProductListAdapter @Inject constructor() : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val ui = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(ui)
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = products[position]
        val itemView = holder.binding
        holder.itemView.apply {
            itemView.productName.text = currentItem.name
            itemView.productPrice.text = (currentItem.price ?: 0.0 .toString()).toString()
            val resName = currentItem.imageName ?: ""
            val resId = context.resources.getIdentifier(resName, "drawable", context.packageName)
            if (resId != 0) {
                itemView.productImage.setImageResource(resId)
            } else {
                itemView.productImage.setImageResource(R.drawable.ic_launcher_foreground)
            }
            setOnClickListener {
                onItemClickListener?.let {
                    it(currentItem.id.toString())
                }
            }
        }

        var likeVectorIsClicked = false
        itemView.likeBtn.setOnClickListener {
            likeVectorIsClicked = !likeVectorIsClicked
            if (likeVectorIsClicked) {
                itemView.likeBtn.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.primary1
                    )
                )
            } else {
                itemView.likeBtn.clearColorFilter()
            }
        }

        itemView.addToBag.setOnClickListener {
            itemView.addToBag.visibility = View.GONE
            itemView.bagCard.visibility = View.VISIBLE
            itemView.productCount.text = "1"
            itemView.productAmountPrice.text = currentItem.price.toString()
        }

        itemView.increaseCount.setOnClickListener {
            var amount = itemView.productCount.text.toString().toDouble()
            var totalPrice = itemView.productAmountPrice.text.toString().toDouble()
            amount++
            totalPrice = roundDecimal((totalPrice + (currentItem.price ?: 0.0) ),3)

            itemView.productCount.text = amount.toString()
            itemView.productAmountPrice.text = totalPrice.toString()
        }

        itemView.decreaseCount.setOnClickListener {
            var amount = itemView.productCount.text.toString().toDouble()
            var totalPrice = itemView.productAmountPrice.text.toString().toDouble()
            amount--
            totalPrice = roundDecimal((totalPrice - (currentItem.price ?: 0.0) ),2)
            if (amount <= 0.0){
                itemView.productCount.text = "1"
                itemView.addToBag.visibility = View.VISIBLE
                itemView.bagCard.visibility = View.GONE
            }
            itemView.productCount.text = amount.toString()
            itemView.productAmountPrice.text = totalPrice.toString()
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)
    var products: MutableList<Product>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    private fun roundDecimal(number: Double, decimalPlaces: Int): Double {
        val factor = 10.0.pow(decimalPlaces)
        return (number * factor).roundToInt() / factor
    }
}