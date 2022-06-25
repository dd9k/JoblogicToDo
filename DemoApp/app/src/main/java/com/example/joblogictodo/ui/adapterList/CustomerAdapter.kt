package com.example.joblogictodo.ui.adapterList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joblogictodo.databinding.ItemCustomerBinding
import com.example.joblogictodo.domain.models.Customer
import javax.inject.Inject


class CustomerAdapter @Inject constructor() : RecyclerView.Adapter<CustomerAdapter.RecyclerViewHolder>() {

    var dataList: List<Customer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) = holder.bind(dataList[position])

    override fun getItemCount(): Int = dataList.size

    class RecyclerViewHolder(private val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Customer) {
            binding.item = item
        }
    }
}