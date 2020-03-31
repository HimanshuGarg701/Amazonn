package com.example.amazonn

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.amazonn.databinding.ProductListItemBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductListAdapter(private val products : ArrayList<Product>) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val binding = ProductListItemBinding.inflate(layoutInflate, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        Picasso.get().load(product.imageURL).into(holder.itemView.productImage)
    }



    class ProductViewHolder(binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root){
        private val productName = binding.productName
        private val productPrice = binding.productPrice

        fun bind(product: Product) {
            productName.text = product.name
            productPrice.text = (" \$ ${product.price.toString()}")
        }
    }
}

