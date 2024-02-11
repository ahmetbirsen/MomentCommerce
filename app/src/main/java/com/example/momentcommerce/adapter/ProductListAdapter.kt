package com.example.momentcommerce.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.momentcommerce.R
import com.example.momentcommerce.databinding.FragmentOrderConfirmationBinding
import com.example.momentcommerce.databinding.ItemProductBinding
import com.example.momentcommerce.model.Product
import javax.inject.Inject

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
            val resName = currentItem.imageName ?: ""  // Ürünün resim adını alın
            println("resName : $resName")
            val resId = context.resources.getIdentifier(resName, "drawable", context.packageName)  // Resmin kaynak kimliğini alın
            println("resId : $resId")

            if (resId != 0) {
                itemView.productImage.setImageResource(resId)  // Resmi ImageView'a ayarlayın
            } else {
                // Resim bulunamazsa, varsayılan bir resim ayarlayabilirsiniz
                itemView.productImage.setImageResource(R.drawable.ic_launcher_foreground)
            }
            setOnClickListener {
                onItemClickListener?.let {
                    it(currentItem.id.toString())
                }
            }
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
}