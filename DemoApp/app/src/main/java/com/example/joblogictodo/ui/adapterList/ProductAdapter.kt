package com.example.joblogictodo.ui.adapterList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joblogictodo.databinding.ItemProductBinding
import com.example.joblogictodo.domain.models.Product
import javax.inject.Inject


class ProductAdapter @Inject constructor(): RecyclerView.Adapter<ProductAdapter.RecyclerViewHolder>() {

    var dataList: List<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater)
        return RecyclerViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) = holder.bind(dataList[position])

    override fun getItemCount(): Int = dataList.size

    class RecyclerViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.item = item
        }
    }
}